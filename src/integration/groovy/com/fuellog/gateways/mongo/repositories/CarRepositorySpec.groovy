package com.fuellog.gateways.mongo.repositories

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import com.fuellog.entities.Car
import com.fuellog.gateways.mongo.repositories.entities.CarTemplates
import com.fuellog.gateways.mongo.repositories.entities.TripTemplates
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoOperations
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest
class CarRepositorySpec extends Specification {

    @Autowired
    CarRepository carRepository

    @Autowired
    MongoOperations mongoOperations


    def setup() {
        FixtureFactoryLoader.loadTemplates(TripTemplates.package.name)
    }

    @Unroll
    def "Get car by last trip when: #scenario"() {
        given: "there are trips on database"
        Car carOne = fixture(Car, CarTemplates.BRAND_BMW_MODEL_330I_EFFICIENCY_10)
        Car carTwo = fixture(Car, CarTemplates.BRAND_FORD_MODEL_FOCUS_EFFICIENCY_17)

        mongoOperations.save(carOne)
        mongoOperations.save(carTwo)

        and: "a valid car model"
        String carModel = model

        when: "the method is called"
        def result = carRepository.getCarByModel(carModel)

        then: "result must be correctly"
        if (hasResult) {
            result.id == "unit-test-id-99999902"
        } else {
            result == null
        }

        cleanup:
        mongoOperations.remove(carOne)
        mongoOperations.remove(carTwo)

        where:
        scenario             | model      | hasResult | expectedResult
        "find the model"     | "focus"    | true      | "unit-test-id-99999902"
        "not find the model" | "no-model" | false     | null
    }
    
    def <T> T fixture(Class<T> clazz, String fixture) {
        setup()
        return Fixture.from(clazz).gimme(fixture)
    }

}
