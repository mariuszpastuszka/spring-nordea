package com.mpas.cems;

import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.function.Function;


public class BeanManager {

    public static Function<ApplicationContext, String> asHtml = ctx -> {
        var sb = new StringBuilder("<html><body>");

        sb.append("Hello there dear developer, here are the beans you were looking for: <br>");

        //method that returns all the bean names in the context of the application
        Arrays.stream(ctx.getBeanDefinitionNames()).sorted().forEach(
                beanName -> sb.append("<br>").append(beanName)
        );
        sb.append("</body></htm>");
        return sb.toString();
    };
}
