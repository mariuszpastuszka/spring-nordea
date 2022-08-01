package com.mpas.cems.scopes2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Description;

import java.util.Random;


@Description("Salary for an employee might change, so this is a suitable example for a prototype scoped bean")
public class Salary implements SalaryIdea {
    private Logger logger = LoggerFactory.getLogger(Salary.class);

    private Integer amount;

    public Salary() {
        logger.info(" -> Creating new Salary bean");
        Random rand = new Random();
        this.amount = rand.nextInt(10_000) + 50_000;
    }

    public Integer getAmount() {
        return amount;
    }
}
