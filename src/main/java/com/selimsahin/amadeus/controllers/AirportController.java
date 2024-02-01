package com.selimsahin.amadeus.controllers;

import com.selimsahin.amadeus.models.Airport;
import com.selimsahin.amadeus.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AirportController {
    @Autowired
    private AirportRepository airportRepository;

    @GetMapping("/airports")
    public Iterable<Airport> getAirports() {
        return airportRepository.findAll();
    }

    @PostMapping("/airports")
    public void createAirport(@RequestBody Airport airport) {
        airportRepository.save(airport);
    }
}
