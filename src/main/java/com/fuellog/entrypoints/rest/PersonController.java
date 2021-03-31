package com.fuellog.entrypoints.rest;

import com.fuellog.entities.Person;
import com.fuellog.usecases.GetPerson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/persons", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {
    private final GetPerson getPerson;

    @GetMapping("/{personId}")
    @ResponseStatus(HttpStatus.OK)
    public Person getPersonById(@PathVariable(name = "personId") final Long personId) {
        return getPerson.execute(personId);
    }
}
