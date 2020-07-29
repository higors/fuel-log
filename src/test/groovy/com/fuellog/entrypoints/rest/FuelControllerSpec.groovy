package com.fuellog.entrypoints.rest

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import com.fasterxml.jackson.databind.ObjectMapper
import com.fuellog.commons.configuration.json.ObjectMapperConfiguration
import com.fuellog.entities.Trip
import com.fuellog.entities.TripTemplates
import com.fuellog.usecases.GetLastTrip
import com.fuellog.usecases.ProcessTrip
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification
import spock.mock.DetachedMockFactory

@WebMvcTest(value = FuelController)
@AutoConfigureMockMvc(addFilters = false)
class FuelControllerSpec extends Specification {

    @Autowired
    private MockMvc mvc

    @Autowired
    private GetLastTrip getLastTrip

    ObjectMapper objectMapper = new ObjectMapperConfiguration().getObjectMapper()

    @TestConfiguration
    static class Mocks {
        def factory = new DetachedMockFactory()

        @Bean
        GetLastTrip getLastTrip() {
            factory.Mock(GetLastTrip)
        }

        @Bean
        ProcessTrip processTrip() {
            factory.Mock(ProcessTrip)
        }
    }

    def setup() {
        FixtureFactoryLoader.loadTemplates(TripTemplates.getPackage().name)
    }

    def "Get last trip successfully"() {
        when: "request to the endpoint"
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/v1/trips/last-trip")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()

        then: "get last trip must be called once and returns"
        1 * getLastTrip.execute() >> fixture(Trip, TripTemplates.CAR_MODEL_320_DISTANCE_100_LITERS_15)

        and: "response status must be OK"
        result.response.status == HttpStatus.OK.value()

        and: "response body must be correctly"
        Trip resultBody = objectMapper.readValue(result.getResponse().getContentAsString(), Trip.class)
        resultBody == fixture(Trip, TripTemplates.CAR_MODEL_320_DISTANCE_100_LITERS_15)
    }

    def <T> T fixture(Class<T> clazz, String fixture) {
        setup()
        return Fixture.from(clazz).gimme(fixture)
    }
}
