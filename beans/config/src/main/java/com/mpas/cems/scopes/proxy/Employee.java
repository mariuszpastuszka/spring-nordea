package com.mpas.cems.scopes.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Employee {
    private Salary salary;

    public Employee(Salary salary) {
        this.salary = salary;
    }

    @Autowired
    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public Salary getSalary() {
        return salary;
    }
}
