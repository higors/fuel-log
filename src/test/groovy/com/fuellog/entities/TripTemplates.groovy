package com.fuellog.entities

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader

import static com.fuellog.entities.Trip.Fields.*

class TripTemplates implements TemplateLoader {
    public static String CAR_MODEL_320_DISTANCE_100_LITERS_10 = "Car model 320i distance 100 liters 10"

    @Override
    void load() {
        Fixture.of(Trip.class).addTemplate(CAR_MODEL_320_DISTANCE_100_LITERS_10, new Rule() {
            {
                add(id, "")
                add(carModel, "")
                add(distance, "")
                add(liters, "")
                add(valuePerLiter, "")
                add(createdOn, "")
            }

        })
    }
}
