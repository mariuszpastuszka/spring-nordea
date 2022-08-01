package com.mpas.cems.scopes2;

public interface SalaryIdea {

    Integer getAmount();

    //de-comment this and the lines in the test to witness the proxying of a default method
    /*default Integer defaultMethod() {
        System.out.println("Called from here ->" + this.hashCode() );
        return 0;
    }*/
}
