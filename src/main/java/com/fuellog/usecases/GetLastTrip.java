package com.fuellog.usecases;

import com.fuellog.entities.Trip;
import com.fuellog.gateways.TripGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetLastTrip {

    private final TripGateway tripGateway;

    public Trip execute() {
        return tripGateway.getLastTrip();
    }
}
