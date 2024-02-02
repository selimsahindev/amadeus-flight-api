package com.selimsahin.amadeus.controllers;

import com.selimsahin.amadeus.models.Flight;
import com.selimsahin.amadeus.services.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @GetMapping
    public Iterable<Flight> getFlights() {
        return flightService.getFlights();
    }

    @GetMapping("/{id}")
    public Flight getFlightById(Long id) {
        return flightService.getFlightById(id);
    }

    @PostMapping
    public ResponseEntity<Void> createFlight(@RequestBody Flight flight) {
        flightService.createFlight(flight);
        return new ResponseEntity<>(null, null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlightById(@PathVariable Long id, @RequestBody Flight flight) {
        Flight updatedFlight = flightService.updateFlightById(id, flight);

        if (updatedFlight == null) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedFlight, null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlightById(@PathVariable Long id) {
        flightService.deleteFlightById(id);
        return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);
    }
}
