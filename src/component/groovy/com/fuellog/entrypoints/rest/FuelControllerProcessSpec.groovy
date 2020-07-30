package com.fuellog.entrypoints.rest

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import com.fasterxml.jackson.databind.ObjectMapper
import com.fuellog.commons.configuration.json.ObjectMapperConfiguration
import com.fuellog.entities.Car
import com.fuellog.entities.Trip
import com.fuellog.entities.TripTemplates
import com.fuellog.gateways.mongo.CarGatewayMongoImpl
import com.fuellog.gateways.mongo.repositories.CarRepository
import com.fuellog.usecases.ProcessTrip
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.ApplicationContext
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import spock.lang.Specification
import spock.lang.Unroll

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request

@ContextConfiguration(classes = [
        FuelControllerProcessConfig, FuelController,

        /* Usecases */
        ProcessTrip,

        /* Gateway */
        CarGatewayMongoImpl
])
@WebMvcTest(value = FuelController)
class FuelControllerProcessSpec extends Specification {

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private ApplicationContext context

    @Autowired
    private CarRepository carRepository

    def <T> T fixture(Class<T> clazz, String fixture) {
        setup()
        return Fixture.from(clazz).gimme(fixture)
    }

    def setup() {
        FixtureFactoryLoader.loadTemplates(TripTemplates.package.name)
    }

    ObjectMapper objectMapper = new ObjectMapperConfiguration().getObjectMapper()

    def path = "/v1/trips/"

    @Unroll
    def "Process trip: #scenario"() {
        given: "a known trip"
        Trip trip = fixture(Trip, TripTemplates.CAR_MODEL_01_DISTANCE_100_LITERS_15)

        when: "is requested to process this trip"
        MvcResult result = mockMvc
                .perform(request(HttpMethod.POST, "$path${"process"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(trip))
                )
                .andReturn()

        then: "is got the correct car in database"
        repositoryCalls * carRepository.getCarByModel(_ as String) >> { String carModel ->
            assert carModel == trip.carModel
            return car
        }

        expect: "the result message is correct"
        result.getResponse().getStatus() == status.value()
        result.getResponse().getContentAsString() == response

        where:
        scenario    | repositoryCalls | car       | status        | response
        "good trip" | 1               | getCar(1) | HttpStatus.OK | "Parabens voce e um amigo do meio ambiente"
        "bad trip"  | 1               | getCar(8) | HttpStatus.OK | "Que pena o seu consumo foi horrivel, ande mais devagar da proxima vez"
    }


    def getCar(int efficiency) {
        return Car.builder()
                .efficiency(BigDecimal.valueOf(efficiency))
                .build()
    }

}
