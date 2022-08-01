package com.mpas.cems.beans.misc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MiscAppCfg {

    @Bean
    SimpleSingleton simpleSingleton() {
        return SimpleSingleton.getInstance();
    }

    @Bean
    TaxFormula taxNoFormula() {
        return new TaxFormulaFactory().getTaxFormula("NT");
    }

    @Bean
    TaxFormula taxScotlandFormula() {
        return new TaxFormulaFactory().getTaxFormula("S");
    }
}
