package com.fuellog.gateways.postgresql;

import com.fuellog.entities.Person;
import com.fuellog.gateways.PersonGateway;
import com.fuellog.gateways.postgresql.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonGatewayPostgresImpl implements PersonGateway {
    private final PersonRepository personRepository;

    @Override
    public Person getPerson(final Long personId) {
        return personRepository.getOne(personId);
    }
}
