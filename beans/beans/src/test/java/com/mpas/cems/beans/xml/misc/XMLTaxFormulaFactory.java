package com.mpas.cems.beans.xml.misc;

import com.mpas.cems.beans.misc.NoTaxFormula;
import com.mpas.cems.beans.misc.ScotlandRateFormula;
import com.mpas.cems.beans.misc.TaxFormula;


public class XMLTaxFormulaFactory {

    private String taxFormula;

    public void setTaxFormula(String taxFormula) {
        this.taxFormula = taxFormula;
    }

    public TaxFormula getTaxFormula() {
        if (taxFormula == null) {
            return null;
        }

        switch (taxFormula) {
            case "S":
                return new ScotlandRateFormula();
            case "NT":
                return new NoTaxFormula();
        }
        return null;
    }

}
