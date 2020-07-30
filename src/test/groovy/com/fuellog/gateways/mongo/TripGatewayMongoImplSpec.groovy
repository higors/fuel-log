package com.fuellog.gateways.mongo

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import com.fuellog.entities.Trip
import com.fuellog.entities.TripTemplates
import com.fuellog.gateways.mongo.repositories.TripRepository
import spock.lang.Specification

class TripGatewayMongoImplSpec extends Specification {

    TripRepository tripRepository = Mock()
    TripGatewayMongoImpl tripGatewayMongo = new TripGatewayMongoImpl(tripRepository)

    def setup() {
        FixtureFactoryLoader.loadTemplates(TripTemplates.package.name)
    }

    def "Get last trip successfully"() {
        when: "the method is called"
        Trip result = tripGatewayMongo.getLastTrip()

        then: "trip repository must be called once and returns"
        tripRepository.getLastTrip() >> {
            return fixture(Trip, TripTemplates.CAR_MODEL_320_DISTANCE_100_LITERS_10)
        }

        and: "result must be correctly"
        result == fixture(Trip, TripTemplates.CAR_MODEL_320_DISTANCE_100_LITERS_10)
    }

    def <T> T fixture(Class<T> clazz, String fixture) {
        setup()
        return Fixture.from(clazz).gimme(fixture)
    }
}
