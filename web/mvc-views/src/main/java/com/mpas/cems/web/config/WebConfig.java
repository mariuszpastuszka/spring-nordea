
package com.mpas.cems.web.config;

import com.mpas.cems.web.resolver.JsonViewResolver;
import com.mpas.cems.web.util.Properties;
import com.mpas.cems.web.views.PersonExcelView;
import com.mpas.cems.web.views.PersonPdfView;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.mpas.cems.web.controllers"})
class WebConfig implements WebMvcConfigurer, ApplicationContextAware, ServletContextAware {

    private ApplicationContext applicationContext;
    private ServletContext servletContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        var springTemplateResolver = new SpringResourceTemplateResolver();
        springTemplateResolver.setApplicationContext(this.applicationContext);
        springTemplateResolver.setPrefix("/WEB-INF/");
        springTemplateResolver.setSuffix(".html");
        springTemplateResolver.setTemplateMode("HTML5");
        springTemplateResolver.setCharacterEncoding("UTF-8");
        return springTemplateResolver;
    }

    @Bean
    ContentNegotiatingViewResolver viewResolver(){
        var factory = new ContentNegotiationManagerFactoryBean();
        factory.setDefaultContentType(MediaType.TEXT_HTML);
        factory.setIgnoreAcceptHeader(true);
        factory.setFavorParameter(false);
        factory.setFavorPathExtension(true);
        factory.setMediaTypes(Properties.of("html", MediaType.TEXT_HTML_VALUE,"xls", "application/vnd.ms-excel",
                "pdf", MediaType.APPLICATION_PDF_VALUE, "json", MediaType.APPLICATION_JSON_VALUE));

        var  resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(factory.getObject());
        resolver.setOrder(-1);
        resolver.setDefaultViews(defaultViewsList());
        resolver.setViewResolvers(resolverList());
        return resolver;
    }

    private List<View> defaultViewsList(){
        List<View> views = new ArrayList<>();
        views.add(new PersonExcelView());
        views.add(new PersonPdfView());
        views.add(new MappingJackson2JsonView());
        return views;
    }

    private List<ViewResolver> resolverList(){
        List<ViewResolver> resolvers = new ArrayList<>();
        resolvers.add(new BeanNameViewResolver());
        resolvers.add(thymeViewResolver());
        resolvers.add(new JsonViewResolver());
        return resolvers;
    }

    @Bean
    @Description("Thymeleaf Template Engine")
    public SpringTemplateEngine templateEngine() {
        var templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setTemplateEngineMessageSource(messageSource());
        return templateEngine;
    }

    @Bean
    @Description("Thymeleaf View Resolver")
    public ThymeleafViewResolver thymeViewResolver() {
        var viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setOrder(0);
        return viewResolver;
    }

    // <=> <mvc:default-servlet-handler/>
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public Validator validator() {
        final var validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    @Override
    public Validator getValidator() {
        return validator();
    }

    //Declare our static resources. I added cache to the java config but it?s not required.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**", "/styles/**")
                .addResourceLocations("/WEB-INF/images/", "/WEB-INF/styles/").setCachePeriod(31556926);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/home");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/*");
        registry.addInterceptor(themeChangeInterceptor());
        registry.addInterceptor(webChangeInterceptor());
    }

    @Bean
    MessageSource messageSource() {
        var messageResource = new ReloadableResourceBundleMessageSource();
        messageResource.setBasename("classpath:i18n/global");
        messageResource.setDefaultEncoding("UTF-8");
        messageResource.setUseCodeAsDefaultMessage(true);
        // # -1 : never reload, 0 always reload
        //messageResource.setCacheSeconds(0);
        return messageResource;
    }

    @Bean
    LocaleChangeInterceptor localeChangeInterceptor() {
        var localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Bean
    ThemeChangeInterceptor themeChangeInterceptor() {
        var themeChangeInterceptor = new ThemeChangeInterceptor();
        themeChangeInterceptor.setParamName("theme");
        return themeChangeInterceptor;
    }

    @Bean
    CookieLocaleResolver localeResolver() {
        var cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        cookieLocaleResolver.setCookieMaxAge(3600);
        cookieLocaleResolver.setCookieName("locale");
        return cookieLocaleResolver;
    }

    @Bean
    CookieThemeResolver themeResolver() {
        var cookieThemeResolver = new CookieThemeResolver();
        cookieThemeResolver.setDefaultThemeName("green");
        cookieThemeResolver.setCookieMaxAge(3600);
        cookieThemeResolver.setCookieName("theme");
        return cookieThemeResolver;
    }

    @Bean
    WebContentInterceptor webChangeInterceptor() {
        var webContentInterceptor = new WebContentInterceptor();
        webContentInterceptor.setCacheSeconds(0);
        webContentInterceptor.setSupportedMethods("GET", "POST", "PUT", "DELETE");
        return webContentInterceptor;
    }

}