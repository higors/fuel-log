package com.fuellog.gateways.mongo.repositories;

import com.fuellog.entities.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TripRepository {
    private final MongoOperations mongoOperations;

    public Trip getLastTrip() {
        final Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "createdOn"));
        return mongoOperations.findOne(query, Trip.class);
    }
}
