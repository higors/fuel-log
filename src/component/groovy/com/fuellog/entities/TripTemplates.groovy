package com.fuellog.entities

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import com.fuellog.entities.Trip

import java.time.Instant

import static com.fuellog.entities.Trip.Fields.*

class TripTemplates implements TemplateLoader {
    public static String CAR_MODEL_01_DISTANCE_100_LITERS_15 = "Car model integration-01 distance 100 liters 15"
    public static String CAR_MODEL_02_DISTANCE_100_LITERS_15 = "Car model integration-01 distance 100 liters 15"

    @Override
    void load() {
        Fixture.of(Trip.class).addTemplate(CAR_MODEL_01_DISTANCE_100_LITERS_15, new Rule() {
            {

                add(id, "integration-id-99999901")
                add(carModel, "integration-model-01")
                add(distance, BigDecimal.valueOf(100))
                add(liters, BigDecimal.valueOf(15))
                add(valuePerLiter, BigDecimal.valueOf(5.85))
                add(createdOn, Instant.parse("2020-01-05T10:35:00Z"))
            }
        })

        Fixture.of(Trip.class).addTemplate(CAR_MODEL_02_DISTANCE_100_LITERS_15).inherits(CAR_MODEL_01_DISTANCE_100_LITERS_15, new Rule() {
            {
                add(id, "integration-id-99999902")
                add(carModel, "integration-model-02")
                add(createdOn, Instant.parse("2020-07-20T10:35:00Z"))
            }
        })
    }
}