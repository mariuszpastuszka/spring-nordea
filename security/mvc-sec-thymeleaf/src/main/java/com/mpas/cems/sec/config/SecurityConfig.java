
package com.mpas.cems.sec.config;

import com.mpas.cems.ex.ConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/resources/**","/images/**","/styles/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        try {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            auth
                    .inMemoryAuthentication()
                    .passwordEncoder(passwordEncoder)
                    .withUser("john").password(passwordEncoder.encode("doe")).roles("USER")
                    .and().withUser("jane").password(passwordEncoder.encode("doe")).roles("USER", "ADMIN")
                    .and().withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN");
        } catch (Exception e) {
            throw new ConfigurationException("In-Memory authentication was not configured.", e);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/resources/**","/images/**","/styles/**").permitAll()
                .mvcMatchers("/persons/*/edit").hasRole("ADMIN")
                .mvcMatchers("/detectives/**").hasRole("ADMIN")
                .mvcMatchers("/**").hasAnyRole("ADMIN","USER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
/*                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .loginPage("/auth")
                .failureUrl("/auth?auth_error=1")*/
                .defaultSuccessUrl("/home")
                .permitAll()
                .and()
                .logout()
/*                .logoutUrl("/logout")*/
                .logoutSuccessUrl("/")
                .and()
                .csrf().csrfTokenRepository(repo());
    }

    @Bean
    public CsrfTokenRepository repo() {
        HttpSessionCsrfTokenRepository repo = new HttpSessionCsrfTokenRepository();
        repo.setParameterName("_csrf");
        repo.setHeaderName("X-CSRF-TOKEN");
        return repo;
    }
}
