package com.mpas.cems.beans;


// TODO 6. Add  a bean definition
public class Book implements Item {

    private String title;

    // TODO 7. Add a constructor  that sets the value for the title property
    // and declare a value to be injected

    @Override
    public String getTitle() {
        return title;
    }
}
