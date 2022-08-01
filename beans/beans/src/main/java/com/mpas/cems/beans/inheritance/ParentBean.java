package com.mpas.cems.beans.inheritance;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class ParentBean {

    @Value("Smith")
    protected String familyName;

    protected String surname;

    ParentBean(@Value("John") String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "ParentBean{" +
                " familyName='" + familyName + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    String getFamilyName() {
        return familyName;
    }

    String getSurname() {
        return surname;
    }
}
