package com.fuellog.gateways.mongo.repositories;

import com.fuellog.entities.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CarRepository {
    private final MongoOperations mongoOperations;

    public Car getCarByModel(final String model) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("model").is(model));
        return mongoOperations.findOne(query, Car.class);
    }
}
