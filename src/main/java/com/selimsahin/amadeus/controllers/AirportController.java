package com.selimsahin.amadeus.controllers;

import com.selimsahin.amadeus.dtos.AirportCreateDto;
import com.selimsahin.amadeus.dtos.AirportResponseDto;
import com.selimsahin.amadeus.models.Airport;
import com.selimsahin.amadeus.repositories.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/airports")
@RequiredArgsConstructor
public class AirportController {
    private final AirportRepository airportRepository;

    @GetMapping
    public Iterable<AirportResponseDto> getAirports() {
        return airportRepository.findAll().stream().map(airport -> {
            AirportResponseDto airportResponseDto = new AirportResponseDto();
            airportResponseDto.setId(airport.getId());
            airportResponseDto.setCity(airport.getCity());
            airportResponseDto.setCreatedAt(airport.getCreatedAt());
            airportResponseDto.setUpdatedAt(airport.getUpdatedAt());
            return airportResponseDto;
        }).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportResponseDto> getAirport(@PathVariable Long id) {
        Optional<Airport> airport = airportRepository.findById(id);

        if (airport.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AirportResponseDto airportResponseDto = new AirportResponseDto();
        airportResponseDto.setId(airport.get().getId());
        airportResponseDto.setCity(airport.get().getCity());
        airportResponseDto.setCreatedAt(airport.get().getCreatedAt());
        airportResponseDto.setUpdatedAt(airport.get().getUpdatedAt());

        return new ResponseEntity<>(airportResponseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AirportResponseDto> createAirport(@RequestBody AirportCreateDto airportCreateDto) {
        Airport airport = new Airport();
        airport.setCity(airportCreateDto.getCity());
        airportRepository.save(airport);

        AirportResponseDto airportResponseDto = new AirportResponseDto();
        airportResponseDto.setId(airport.getId());
        airportResponseDto.setCity(airport.getCity());
        airportResponseDto.setCreatedAt(airport.getCreatedAt());
        airportResponseDto.setUpdatedAt(airport.getUpdatedAt());

        return new ResponseEntity<>(airportResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportResponseDto> updateAirport(@PathVariable Long id, @RequestBody AirportCreateDto airportCreateDto) {
        Optional<Airport> optionalAirport = airportRepository.findById(id);

        if (optionalAirport.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Airport airport = optionalAirport.get();
        airport.setCity(airportCreateDto.getCity());
        airportRepository.save(airport);

        AirportResponseDto airportResponseDto = new AirportResponseDto();
        airportResponseDto.setId(airport.getId());
        airportResponseDto.setCity(airport.getCity());
        airportResponseDto.setCreatedAt(airport.getCreatedAt());
        airportResponseDto.setUpdatedAt(airport.getUpdatedAt());

        return new ResponseEntity<>(airportResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAirport(@PathVariable Long id) {
        Optional<Airport> optionalAirport = airportRepository.findById(id);

        if (optionalAirport.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        airportRepository.delete(optionalAirport.get());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
