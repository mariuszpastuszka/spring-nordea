package com.mpas.cems.beans.inheritance;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class ChildBean extends ParentBean {

    private Boolean adult;

    public ChildBean(@Value("Lil' John") String surname, @Value("false") Boolean adult) {
        super(surname);
        this.adult = adult;
    }

    @Override
    public String toString() {
        return "ChildBean{" +
                " familyName='" + familyName + '\'' +
                ", surname='" + surname + '\'' +
                ", isAdult=" + adult +
                '}';
    }
}
