package com.mpas.cems.beans.misc;


public class TaxFormulaFactory {

    public TaxFormula getTaxFormula(final String taxPlanCode) {
        if (taxPlanCode == null) {
            return null;
        }

        switch (taxPlanCode) {
            case "S":
                return new ScotlandRateFormula();
            case "NT":
                return new NoTaxFormula();
        }
        return null;
    }

}
