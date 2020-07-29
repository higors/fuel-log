package com.fuellog.gateways.mongo;

import com.fuellog.entities.Trip;
import com.fuellog.gateways.TripGateway;
import com.fuellog.gateways.mongo.repositories.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TripGatewayMongoImpl implements TripGateway {

    private final TripRepository tripRepository;

    @Override
    public Trip getLastTrip() {
        return tripRepository.getLastTrip();
    }
}
