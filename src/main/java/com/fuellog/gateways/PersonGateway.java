package com.fuellog.gateways;

import com.fuellog.entities.Person;

public interface PersonGateway {
    Person getPerson(final Long personId);
}
