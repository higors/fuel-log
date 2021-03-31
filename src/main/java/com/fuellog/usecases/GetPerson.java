package com.fuellog.usecases;

import com.fuellog.entities.Person;
import com.fuellog.gateways.PersonGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPerson {
    private final PersonGateway personGateway;

    public Person execute(final Long personId) {
        return personGateway.getPerson(personId);
    }
}
