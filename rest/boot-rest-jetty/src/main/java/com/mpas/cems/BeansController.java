
package com.mpas.cems;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@RestController
class BeansController implements ApplicationContextAware {
    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    @GetMapping("/")
    Map<String,String> allBeans() {
        Map<String,String> map = new HashMap<>();
        Arrays.stream(ctx.getBeanDefinitionNames()).forEach(beanName -> {
            map.put(beanName, ctx.getBean(beanName).getClass().toString());
        });
        return map;
    }
}
