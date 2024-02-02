package com.selimsahin.amadeus.services;

import com.selimsahin.amadeus.models.Flight;
import com.selimsahin.amadeus.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;

    public Flight createFlight(Flight flight) {
        System.out.println("Flight Created");
        return flightRepository.save(flight);
    }

    public Iterable<Flight> getFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        Optional<Flight> foundFlight = flightRepository.findById(id);

        if (foundFlight.isEmpty()) {
            throw new RuntimeException("Flight not found");
        }

        return foundFlight.get();
    }

    public Flight updateFlightById(Long id, Flight flight) {
        Optional<Flight> foundFlight = flightRepository.findById(id);

        if (foundFlight.isEmpty()) {
            return null;
        }

        foundFlight.get().setDepartureAirport(flight.getDepartureAirport());
        foundFlight.get().setArrivalAirport(flight.getArrivalAirport());
        foundFlight.get().setDepartureDateTime(flight.getDepartureDateTime());
        foundFlight.get().setReturnDateTime(flight.getReturnDateTime());
        foundFlight.get().setPrice(flight.getPrice());

        return flightRepository.save(foundFlight.get());
    }

    public void deleteFlightById(Long id) {
        flightRepository.deleteById(id);
    }
}
