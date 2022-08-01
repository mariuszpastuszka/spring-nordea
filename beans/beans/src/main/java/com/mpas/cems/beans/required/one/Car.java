package com.mpas.cems.beans.required.one;

import com.mpas.cems.beans.required.SteeringWheel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Car {
    private SteeringWheel steeringWheel;

    @Autowired(required = false)
    public Car(SteeringWheel steeringWheel) {
        this.steeringWheel = steeringWheel;
    }

    @Override
    public String toString() {
        return "Car{" +
                "steeringWheel=" + steeringWheel +
                '}';
    }
}
