package com.mpas.cems.beans.xml.misc;

import com.mpas.cems.beans.misc.ScotlandRateFormula;
import com.mpas.cems.beans.misc.TaxFormula;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;


public class TaxFormulaFactoryBean implements FactoryBean<TaxFormula> {

    private Logger logger = LoggerFactory.getLogger(TaxFormulaFactoryBean.class);
    private TaxFormula taxFormula = new ScotlandRateFormula();


    public TaxFormulaFactoryBean() {
        logger.info(">> Look ma, no definition!");
    }

    @Override
    public TaxFormula getObject() {
        return this.taxFormula;
    }

    @Override
    public Class<?> getObjectType() {
        return ScotlandRateFormula.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
