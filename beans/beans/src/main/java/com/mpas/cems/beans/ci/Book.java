package com.mpas.cems.beans.ci;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class Book implements Item {

    private String title;

    public Book(@Value("Nevermore") String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
