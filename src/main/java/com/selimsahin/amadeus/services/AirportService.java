package com.selimsahin.amadeus.services;

import com.selimsahin.amadeus.models.Airport;
import com.selimsahin.amadeus.repositories.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;

    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public Iterable<Airport> getAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirportById(Long id) {
        Optional<Airport> foundAirport = airportRepository.findById(id);

        if (foundAirport.isEmpty()) {
            throw new RuntimeException("Airport not found");
        }

        return foundAirport.get();
    }

    public Airport updateAirportById(Long id, Airport airport) {
        Optional<Airport> foundAirport = airportRepository.findById(id);

        if (foundAirport.isEmpty()) {
            return null;
        }

        foundAirport.get().setCity(airport.getCity());
        return airportRepository.save(foundAirport.get());
    }

    public void deleteAirportById(Long id) {
        airportRepository.deleteById(id);
    }
}
