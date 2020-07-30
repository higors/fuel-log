package com.fuellog.gateways.mongo

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import com.fuellog.entities.Car
import com.fuellog.entities.CarTemplates
import com.fuellog.gateways.mongo.repositories.CarRepository
import spock.lang.Specification

class CarGatewayMongoImplSpec extends Specification {

    CarRepository carRepository = Mock()

    CarGatewayMongoImpl carGatewayMongoImpl = new CarGatewayMongoImpl(carRepository)

    def setup() {
        FixtureFactoryLoader.loadTemplates(CarTemplates.package.name)
    }

    def "get efficiency by car model successfully"() {
        given: "a valid car model"
        String carModel = "car model"
        when: "the method is called"
        BigDecimal result = carGatewayMongoImpl.getAverageEfficiencyByCarModel(carModel);

        then: "car repository must be called with correctly values and returns"
        1 * carRepository.getCarByModel(carModel) >> {
            fixture(Car, CarTemplates.BRAND_BMW_MODEL_320I_EFFICIENCY_10)
        }

        and: "result must be correctly"
        result == BigDecimal.valueOf(10)
    }

    def <T> T fixture(Class<T> clazz, String fixture) {
        setup()
        return Fixture.from(clazz).gimme(fixture)
    }
}
