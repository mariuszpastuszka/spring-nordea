package com.mpas.cems.beans.required.four;

import com.mpas.cems.beans.required.SteeringWheel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class OptionalPartsCar {
    private SteeringWheel steeringWheel;

    // You can trick the Spring Constructor injection like this to accept an empty value but,
    // it is not recommended to use Optional as parameter type.
    @Autowired
    public OptionalPartsCar(Optional<SteeringWheel> steeringWheelOpt) {
        steeringWheelOpt.ifPresentOrElse(sw -> this.steeringWheel = sw, () -> steeringWheel = null);
    }

    @Override
    public String toString() {
        return "OptionalPartsCar{" +
                "steeringWheel=" + steeringWheel +
                '}';
    }
}
