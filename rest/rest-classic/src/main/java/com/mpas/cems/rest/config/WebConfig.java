
package com.mpas.cems.rest.config;

import com.mpas.cems.dj.ServiceConfig;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletContext;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.mpas.cems.rest.controllers"})
public class WebConfig implements WebMvcConfigurer, WebApplicationInitializer {

    @Bean
    public ObjectMapper objectMapper() {
        var objMapper = new ObjectMapper();
        objMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objMapper.findAndRegisterModules();
        return objMapper;
    }

    /*@Override
    public void onStartup(ServletContext servletContext) {
        var rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(H2DbConfig.class, ServiceConfig.class, WebConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));

        var dispatcher =
                servletContext.addServlet("cems-dispatcher", new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }*/

    @Override
    public void onStartup(ServletContext servletContext) {
        var rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(H2DbConfig.class, ServiceConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));

        var dispatcherContext =
                new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(WebConfig.class);

        var dispatcher =
                servletContext.addServlet("cems-dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}