package com.fuellog.entrypoints.rest;

import com.fuellog.entities.Trip;
import com.fuellog.usecases.GetLastTrip;
import com.fuellog.usecases.ProcessTrip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/trips", produces = MediaType.APPLICATION_JSON_VALUE)
public class FuelController {

    private final GetLastTrip getLastTrip;
    private final ProcessTrip processTrip;

    @GetMapping("/last-trip")
    @ResponseStatus(HttpStatus.OK)
    public Trip getLastTrip() {
        return getLastTrip.execute();
    }

    @PostMapping("/process")
    @ResponseStatus(HttpStatus.OK)
    public String process(@RequestBody final Trip trip) {
        return processTrip.execute(trip);
    }

}
