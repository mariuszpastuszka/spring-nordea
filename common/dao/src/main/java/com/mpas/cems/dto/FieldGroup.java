package com.mpas.cems.dto;

public enum FieldGroup {
    FIRSTNAME,
    LASTNAME,
    USERNAME,
    HIREDIN;

    public static FieldGroup getField(String field) {
        return FieldGroup.valueOf(field.toUpperCase());
    }
}
