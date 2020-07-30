package com.fuellog.usecases

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import com.fuellog.entities.Trip
import com.fuellog.entities.TripTemplates
import com.fuellog.gateways.TripGateway
import spock.lang.Specification

class GetLastTripSpec extends Specification {
    TripGateway tripGateway = Mock()
    GetLastTrip getLastTrip = new GetLastTrip(tripGateway)

    def setup() {
        FixtureFactoryLoader.loadTemplates(TripTemplates.package.name)
    }

    def "Get last trip successfully"() {
        when: "the method is called"
        def result = getLastTrip.execute()

        then: "trip gateway must be called once and returns"
        1 * tripGateway.getLastTrip() >> {
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
