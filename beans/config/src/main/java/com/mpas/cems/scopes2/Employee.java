package com.mpas.cems.scopes2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Employee {
    private SalaryIdea salary;

    public Employee(SalaryIdea salary) {
        this.salary = salary;
    }

    @Autowired
    public void setSalary(SalaryIdea salary) {
        this.salary = salary;
    }

    public SalaryIdea getSalary() {
        return salary;
    }
}
