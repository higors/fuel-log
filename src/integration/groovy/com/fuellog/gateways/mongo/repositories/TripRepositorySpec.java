package com.fuellog.gateways.mongo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import spock.lang.Specification;

@SpringBootTest
class TripRepositorySpec extends Specification {
    @Autowired
    MongoOperations mongoOperations;
}
