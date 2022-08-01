package com.mpas.cems.beans.ci;

import org.springframework.stereotype.Component;


@Component
public class Person implements Human {

    private Item item;

    //@Autowired
    public Person(Item item) {
        this.item = item;
    }

    @Override
    public Item getItem() {
        return item;
    }
}
