package com.fuellog.gateways.mongo.repositories

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import com.fuellog.entities.Trip
import com.fuellog.gateways.mongo.repositories.entities.TripTemplates
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoOperations
import spock.lang.Specification

import java.time.Instant

@SpringBootTest
class TripRepositorySpec extends Specification {

    @Autowired
    TripRepository tripRepository

    @Autowired
    MongoOperations mongoOperations


    def setup() {
        FixtureFactoryLoader.loadTemplates(TripTemplates.package.name)
    }

    def "Get last trip successfully"() {
        given: "there are trips on database"
        Trip tripOne = fixture(Trip, TripTemplates.CAR_MODEL_01_DISTANCE_100_LITERS_15)
        Trip tripTwo = fixture(Trip, TripTemplates.CAR_MODEL_02_DISTANCE_100_LITERS_15)

        mongoOperations.save(tripOne)
        mongoOperations.save(tripTwo)

        when: "the method is called"
        def result = tripRepository.getLastTrip()

        then: "result must be correctly"
        result.carModel == "integration-model-02"
        result.createdOn == Instant.parse("2020-07-20T10:35:00Z")

        cleanup:
        mongoOperations.remove(tripOne)
        mongoOperations.remove(tripTwo)
    }


    def <T> T fixture(Class<T> clazz, String fixture) {
        setup()
        return Fixture.from(clazz).gimme(fixture)
    }

}
