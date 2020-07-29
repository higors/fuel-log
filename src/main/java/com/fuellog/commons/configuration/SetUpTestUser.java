package com.fuellog.commons.configuration;

import com.fuellog.entities.Car;
import com.fuellog.entities.Trip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.Instant;


@Slf4j
@Configuration
public class SetUpTestUser {

    @Autowired
    MongoTemplate mongoTemplate;

    @PostConstruct
    public void setUpTestUser() {
        try {
            cleanDatabase();
            initData();
        } catch (final Exception e) {
            log.error("setup user error ", e);
        }
    }

    private void cleanDatabase() {
        mongoTemplate.dropCollection(Car.class);
        mongoTemplate.dropCollection(Trip.class);
    }

    private void initData() {
        final Car bmw = createCar("bmw", "320i", BigDecimal.valueOf(10));
        final Car ford = createCar("ford", "fusion", BigDecimal.valueOf(13.5));
        final Trip tripOne = createTrip("fusion", BigDecimal.valueOf(85), BigDecimal.valueOf(10), BigDecimal.valueOf(4.70), Instant.parse("2020-03-02T15:10:00Z"));
        final Trip tripTwo = createTrip("320i", BigDecimal.valueOf(78), BigDecimal.valueOf(15), BigDecimal.valueOf(5.30), Instant.parse("2020-04-15T20:57:00Z"));
        mongoTemplate.save(bmw);
        mongoTemplate.save(ford);
        mongoTemplate.save(tripOne);
        mongoTemplate.save(tripTwo);
    }

    private Trip createTrip(final String model,
                            final BigDecimal distance,
                            final BigDecimal liters,
                            final BigDecimal valuePerLiter,
                            final Instant createdOn) {
        return Trip.builder()
            .carModel(model)
            .distance(distance)
            .liters(liters)
            .valuePerLiter(valuePerLiter)
            .createdOn(createdOn)
            .build();
    }

    private Car createCar(final String brand, final String model, final BigDecimal efficiency) {
        return Car.builder()
            .brand(brand)
            .model(model)
            .efficiency(efficiency)
            .build();
    }
}
