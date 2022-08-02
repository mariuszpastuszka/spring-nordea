
package com.mpas.cems.web.resolver;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Locale;


public class JsonViewResolver  implements ViewResolver, Ordered {
    private int order;

    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public View resolveViewName(String viewName, Locale locale) {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        //make JSON output readable
        view.setPrettyPrint(true);
        return view;
    }
}
