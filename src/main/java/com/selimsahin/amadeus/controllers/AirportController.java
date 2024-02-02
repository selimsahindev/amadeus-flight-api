package com.selimsahin.amadeus.controllers;

import com.selimsahin.amadeus.models.Airport;
import com.selimsahin.amadeus.repositories.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/airports")
@RequiredArgsConstructor
public class AirportController {
    private final AirportRepository airportRepository;

    @GetMapping
    public Iterable<Airport> getAirports() {
        return airportRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Void> createAirport(@RequestBody Airport airport) {
        airportRepository.save(airport);
        return new ResponseEntity<>(null, null, HttpStatus.CREATED);
    }
}
