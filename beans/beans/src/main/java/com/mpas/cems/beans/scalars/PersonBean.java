package com.mpas.cems.beans.scalars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class PersonBean implements Creature {

    private LocalDateTime birthDate;
    private String name;

    @Autowired
    public PersonBean(@Value("1977-10-16 00:00") LocalDateTime birthDate, @Value("John Mayer") String name) {
        this.birthDate = birthDate;
        this.name = name;
    }

    @Override
    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    @Override
    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonBean{" +
                "birthDate=" + birthDate +
                ", name='" + name + '\'' +
                '}';
    }
}
