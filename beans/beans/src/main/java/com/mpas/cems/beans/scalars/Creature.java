package com.mpas.cems.beans.scalars;

import java.time.LocalDateTime;


public interface Creature {

    LocalDateTime getBirthDate();

    void setBirthDate(LocalDateTime birthDate);

    String getName();

    void setName(String name);
}
