package com.fuellog.gateways.mongo.repositories.entities

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import com.fuellog.entities.Car

import static com.fuellog.entities.Car.Fields.*

class CarTemplates implements TemplateLoader {
    public static String BRAND_BMW_MODEL_330I_EFFICIENCY_10 = "brand bmw model 330i efficiency 10"
    public static String BRAND_FORD_MODEL_FOCUS_EFFICIENCY_17 = "brand ford model focus efficiency 17"

    @Override
    void load() {
        Fixture.of(Car.class).addTemplate(BRAND_BMW_MODEL_330I_EFFICIENCY_10, new Rule() {
            {
                add(id, "unit-test-id-99999901")
                add(brand, "bmw")
                add(model, "330i")
                add(efficiency, BigDecimal.valueOf(10))
            }
        })

        Fixture.of(Car.class).addTemplate(BRAND_FORD_MODEL_FOCUS_EFFICIENCY_17, new Rule() {
            {
                add(id, "unit-test-id-99999902")
                add(brand, "ford")
                add(model, "focus")
                add(efficiency, BigDecimal.valueOf(17))
            }
        })
    }
}
