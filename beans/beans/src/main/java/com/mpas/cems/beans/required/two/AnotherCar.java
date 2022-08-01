package com.mpas.cems.beans.required.two;

import com.mpas.cems.beans.required.SteeringWheel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AnotherCar {
    private SteeringWheel steeringWheel;

    public AnotherCar(@Autowired(required = false) SteeringWheel steeringWheel) {
        this.steeringWheel = steeringWheel;
    }

    @Override
    public String toString() {
        return "AnotherCar{" +
                "steeringWheel=" + steeringWheel +
                '}';
    }
}
