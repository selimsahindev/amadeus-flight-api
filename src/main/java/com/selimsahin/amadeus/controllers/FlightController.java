package com.selimsahin.amadeus.controllers;

import com.selimsahin.amadeus.dtos.FlightCreateDto;
import com.selimsahin.amadeus.dtos.FlightResponseDto;
import com.selimsahin.amadeus.services.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<Iterable<FlightResponseDto>> getFlights() {
        Iterable<FlightResponseDto> flights = flightService.getFlights();
        return new ResponseEntity<>(flights, null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponseDto> getFlightById(@PathVariable Long id) {
        FlightResponseDto foundFlight = flightService.findFlightById(id);
        return ResponseEntity.ok(foundFlight);
    }

    @PostMapping
    public ResponseEntity<FlightResponseDto> createFlight(@RequestBody FlightCreateDto flightCreateDto) {
        FlightResponseDto createdFlight = flightService.createFlight(flightCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFlight);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightResponseDto> updateFlightById(@PathVariable Long id, @RequestBody FlightCreateDto flightCreateDto) {
        FlightResponseDto updatedFlight = flightService.updateFlight(id, flightCreateDto);
        return ResponseEntity.ok(updatedFlight);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFlightById(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
}