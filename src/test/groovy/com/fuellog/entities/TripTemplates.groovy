package com.fuellog.entities

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader

import java.time.Instant

import static com.fuellog.entities.Trip.Fields.*

class TripTemplates implements TemplateLoader {
    public static String CAR_MODEL_320_DISTANCE_100_LITERS_15 = "Car model 320i distance 100 liters 15"

    @Override
    void load() {
        Fixture.of(Trip.class).addTemplate(CAR_MODEL_320_DISTANCE_100_LITERS_15, new Rule() {
            {
                add(carModel, "320i")
                add(distance, BigDecimal.valueOf(100))
                add(liters, BigDecimal.valueOf(15))
                add(valuePerLiter, BigDecimal.valueOf(5.85))
                add(createdOn, Instant.parse("2020-01-01T10:35:00Z"))
            }
        })
    }
}
