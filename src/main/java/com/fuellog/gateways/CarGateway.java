package com.fuellog.gateways;

import java.math.BigDecimal;

public interface CarGateway {
    BigDecimal getEfficiencyByCarModel(final String carModel);
}
