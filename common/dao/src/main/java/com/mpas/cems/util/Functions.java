package com.mpas.cems.util;

import com.mpas.cems.dao.AbstractEntity;

import java.util.Comparator;


public class Functions {

    public static Comparator<AbstractEntity> COMPARATOR_BY_ID = Comparator.comparing(AbstractEntity::getId);
}
