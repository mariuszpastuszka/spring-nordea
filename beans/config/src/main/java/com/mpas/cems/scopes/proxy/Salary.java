package com.mpas.cems.scopes.proxy;

import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.Random;


@Description("Salary for an employee might change, so this is a suitable example for a prototype scoped bean")
@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Salary {

    private Integer amount;

    public Salary() {
        Random rand = new Random();
        this.amount = rand.nextInt(10_000) + 50_000;
    }

    public Integer getAmount() {
        return amount;
    }
}
