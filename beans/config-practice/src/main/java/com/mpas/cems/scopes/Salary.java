package com.mpas.cems.scopes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.Random;


// TODO 14. Redefine this bean to configure JDK interface based proxying. Add classes or interfaces necessary.
// TODO. 15 Create a specialized version of the @Scope annotation you used on this bean to solve requirement 14.
@Description("Salary for an employee might change, so this is a suitable example for a prototype scoped bean")
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Salary {
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
