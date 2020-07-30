package com.fuellog.entrypoints.rest

import com.fuellog.gateways.mongo.repositories.CarRepository
import com.fuellog.usecases.GetLastTrip
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import spock.mock.DetachedMockFactory

@TestConfiguration
class FuelControllerProcessConfig {

    def factory = new DetachedMockFactory()

    @Bean
    CarRepository carRepository() {
        factory.Mock(CarRepository)
    }

    @Bean
    GetLastTrip getLastTrip() {
        factory.Stub(GetLastTrip)
    }

}