package com.mpas.cems.beans.misc;


public class NoTaxFormula implements TaxFormula {
    @Override
    public String getFormula() {
        return "*1.0";
    }
}
