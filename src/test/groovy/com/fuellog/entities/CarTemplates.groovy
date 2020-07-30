package com.fuellog.entities

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader

import static com.fuellog.entities.Car.Fields.*

class CarTemplates implements TemplateLoader {
    public static String BRAND_BMW_MODEL_320I_EFFICIENCY_10 = "brand bmw model 320i efficiency 10"

    @Override
    void load() {
        Fixture.of(Car.class).addTemplate(BRAND_BMW_MODEL_320I_EFFICIENCY_10, new Rule() {
            {
                add(id, "unit-test-id-99999901")
                add(brand, "bmw")
                add(model, "320i")
                add(efficiency, BigDecimal.valueOf(10))
            }
        })
    }
}
