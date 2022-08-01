package com.mpas.cems.scopes.noproxy;

import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Description: When a proxy type is not specified, the scope is associated with the bean being created. This means every time the context
 * is being requested the <code>salary</code> bean, it will create a fresh new one.
 *
 * @since 1.0
 */
@Description("Salary for an employee might change, so this is a suitable example for a prototype scoped bean. This example shows how it shouldn't be used though.")
@Component
@Scope(value = "prototype")
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
