package com.mpas.cems.beans.weird;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class WeirdBean {

    private String name;

    @Autowired
    public WeirdBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
