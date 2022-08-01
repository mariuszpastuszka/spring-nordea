package com.mpas.cems.beans.required.three;

import com.mpas.cems.beans.required.SteeringWheel;
import org.springframework.stereotype.Component;


@Component
public class YetAnotherCar {

    private SteeringWheel steeringWheel;

    // equivalent to @Autowired on constructor
    // equivalent to @Autowired(required=true) on constructor
    public YetAnotherCar(SteeringWheel steeringWheel) {
        this.steeringWheel = steeringWheel;
    }

    @Override
    public String toString() {
        return "YetAnotherCar {" +
                "steeringWheel=" + steeringWheel +
                '}';
    }
}
