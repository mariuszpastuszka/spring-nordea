package com.mpas.cems.beans;


// TODO 8. Add  a bean definition
public class Person implements Human {

    private Item item;

    // TODO 9. Add a constructor and inject a bean of type Item

    @Override
    public Item getItem() {
        return item;
    }
}
