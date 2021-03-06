package com.fuellog.usecases;

import com.fuellog.entities.Trip;
import com.fuellog.gateways.CarGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class ProcessTrip {
    private final CarGateway carGateway;
    private static final String GOOD_MESSAGE = "Parabens voce e um amigo do meio ambiente";
    private static final String BAD_MESSAGE = "Que pena o seu consumo foi horrivel, ande mais devagar da proxima vez";

    public String execute(final Trip trip) {
        final String carModel = trip.getCarModel();
        final BigDecimal distance = trip.getDistance();
        final BigDecimal kmPerLiter = distance.divide(trip.getLiters(), RoundingMode.HALF_EVEN);

        final BigDecimal averageEfficiencyByCarModel = carGateway.getAverageEfficiencyByCarModel(carModel);
        if (kmPerLiter.compareTo(averageEfficiencyByCarModel) >= 0) {
            return GOOD_MESSAGE;
        } else {
            return BAD_MESSAGE;
        }
    }
}
