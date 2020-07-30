package com.fuellog.usecases

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import com.fuellog.entities.Trip
import com.fuellog.entities.TripTemplates
import com.fuellog.gateways.CarGateway
import spock.lang.Specification
import spock.lang.Unroll

class ProcessTripSpec extends Specification {
    CarGateway carGateway = Mock()
    ProcessTrip processTrip = new ProcessTrip(carGateway)

    def setup() {
        FixtureFactoryLoader.loadTemplates(TripTemplates.package.name)
    }

    @Unroll
    def "Process trip when efficiency is #scenario "() {
        given: "a valid trip to process"
        Trip trip = fixture(Trip, tripTemplate)

        when: "the process method is called"
        def result = processTrip.execute(trip)

        then: "car gateway must be called once with correctly values and returns"
        1 * carGateway.getEfficiencyByCarModel(_ as String) >> {
            String cardModelArg ->
                assert cardModelArg == "320i"
                return avarageefficiencyPerLiter
        }

        and: "result must be correctly"
        result == expectedMessage

        where:
        scenario                       | tripTemplate                                       | avarageefficiencyPerLiter | expectedMessage
        "good with higher efficiency"  | TripTemplates.CAR_MODEL_320_DISTANCE_100_LITERS_10 | BigDecimal.valueOf(8)     | "Parabéns você é um amigo do meio ambiente"
        "good with exactly efficiency" | TripTemplates.CAR_MODEL_320_DISTANCE_100_LITERS_10 | BigDecimal.valueOf(10)    | "Parabéns você é um amigo do meio ambiente"
        "bad"                          | TripTemplates.CAR_MODEL_320_DISTANCE_100_LITERS_10 | BigDecimal.valueOf(11)    | "Que pena o seu consumo foi horrível, ande mais de vagar da próxima vez"
    }

    def <T> T fixture(Class<T> clazz, String fixture) {
        setup()
        return Fixture.from(clazz).gimme(fixture)
    }
}
