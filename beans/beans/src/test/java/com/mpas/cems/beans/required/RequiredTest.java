package com.mpas.cems.beans.required;

import com.mpas.cems.beans.required.four.OptionalPartsCar;
import com.mpas.cems.beans.required.four.OptionalPartsCarConfig;
import com.mpas.cems.beans.required.one.Car;
import com.mpas.cems.beans.required.one.CarConfig;
import com.mpas.cems.beans.required.three.YetAnotherCar;
import com.mpas.cems.beans.required.three.YetAnotherCarConfig;
import com.mpas.cems.beans.required.two.AnotherCar;
import com.mpas.cems.beans.required.two.AnotherCarConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class RequiredTest {
    private Logger logger = LoggerFactory.getLogger(RequiredTest.class);

    @Test
    void testCarConfig() {
        Assertions.assertThrows(UnsatisfiedDependencyException.class, () -> {
            var ctx = new AnnotationConfigApplicationContext(CarConfig.class);

            var car = ctx.getBean(Car.class);
            assertNotNull(car);
            logger.debug(car.toString());

            ctx.close();
        });
    }

    @Test
    void testAnotherCarConfig() {
        var ctx = new AnnotationConfigApplicationContext(AnotherCarConfig.class);

        var anotherCar = ctx.getBean(AnotherCar.class);
        assertNotNull(anotherCar);
        logger.debug(anotherCar.toString());

        ctx.close();
    }

    @Test
    void testYetAnotherCarConfig() {
        Assertions.assertThrows(UnsatisfiedDependencyException.class, () -> {
            var ctx = new AnnotationConfigApplicationContext(YetAnotherCarConfig.class);

            var yetAnotherCar = ctx.getBean(YetAnotherCar.class);
            assertNotNull(yetAnotherCar);
            logger.debug(yetAnotherCar.toString());

            ctx.close();
        });
    }

    @Test
    void testOptionalPartsCarConfig() {
        var ctx = new AnnotationConfigApplicationContext(OptionalPartsCarConfig.class);

        var anotherCar = ctx.getBean(OptionalPartsCar.class);
        assertNotNull(anotherCar);
        logger.debug(anotherCar.toString());

        ctx.close();
    }
}
