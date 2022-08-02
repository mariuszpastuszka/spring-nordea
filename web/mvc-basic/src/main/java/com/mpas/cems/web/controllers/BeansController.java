
package com.mpas.cems.web.controllers;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@RestController
class BeansController  implements ApplicationContextAware {
    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    @GetMapping(value = "/beans")
    public String beans() {
        final StringBuilder sb= new StringBuilder("<html><head>Beans</head><body>");
        Arrays.stream(ctx.getBeanDefinitionNames()).forEach(beanName ->
            sb.append(beanName).append(" -- of type -- ").append(ctx.getBean(beanName).getClass().getName()).append("<br/>")
        );
        sb.append("</body></html>");
        return sb.toString();
    }
}
