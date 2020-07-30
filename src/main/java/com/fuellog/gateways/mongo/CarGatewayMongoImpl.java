package com.fuellog.gateways.mongo;

import com.fuellog.entities.Car;
import com.fuellog.gateways.CarGateway;
import com.fuellog.gateways.mongo.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CarGatewayMongoImpl implements CarGateway {

    private final CarRepository carRepository;

    @Override
    public BigDecimal getEfficiencyByCarModel(final String carModel) {
        final Car car = carRepository.getCarByModel(carModel);
        return car.getEfficiency();
    }
}
