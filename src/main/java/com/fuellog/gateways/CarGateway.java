package com.fuellog.gateways;

import java.math.BigDecimal;

public interface CarGateway {
    BigDecimal getAverageEfficiencyByCarModel(final String carModel);
}
