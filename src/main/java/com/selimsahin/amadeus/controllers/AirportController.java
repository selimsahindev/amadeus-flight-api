package com.selimsahin.amadeus.controllers;

import com.selimsahin.amadeus.dtos.AirportCreateDto;
import com.selimsahin.amadeus.dtos.AirportResponseDto;
import com.selimsahin.amadeus.services.AirportService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/airports")
@RequiredArgsConstructor
@SecurityRequirement(name = "basicAuth")
@Tag(name = "Airport", description = "Operations related to airports")
public class AirportController {
    private final AirportService airportService;
    private final ModelMapper modelMapper;

    @GetMapping
    public Iterable<AirportResponseDto> getAirports() {
        return airportService.getAirports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportResponseDto> getAirport(@PathVariable Long id) {
        AirportResponseDto foundAirport = airportService.getAirportById(id);
        return ResponseEntity.ok(foundAirport);
    }

    @PostMapping
    public ResponseEntity<AirportResponseDto> createAirport(@RequestBody AirportCreateDto airportCreateDto) {
        AirportResponseDto createdAirport = airportService.createAirport(airportCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAirport);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportResponseDto> updateAirport(@PathVariable Long id, @RequestBody AirportCreateDto airportCreateDto) {
        AirportResponseDto updatedAirport = airportService.updateAirport(id, airportCreateDto);
        return ResponseEntity.ok(updatedAirport);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
