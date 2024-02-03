package com.selimsahin.amadeus.services;

import com.selimsahin.amadeus.dtos.FlightCreateDto;
import com.selimsahin.amadeus.dtos.FlightResponseDto;
import com.selimsahin.amadeus.exceptions.ResourceNotFoundException;
import com.selimsahin.amadeus.models.Flight;
import com.selimsahin.amadeus.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;

    public FlightResponseDto createFlight(FlightCreateDto flightCreateDto) {
        Flight createdFlight = modelMapper.map(flightCreateDto, Flight.class);
        createdFlight = flightRepository.save(createdFlight);
        return modelMapper.map(createdFlight, FlightResponseDto.class);
    }

    public Iterable<FlightResponseDto> getFlights() {
        List<Flight> flights = flightRepository.findAll();

        return flights.stream()
                .map(flight -> modelMapper.map(flight, FlightResponseDto.class))
                .toList();
    }

    public FlightResponseDto getFlightById(Long id) {
        Optional<Flight> foundFlight = flightRepository.findById(id);

        if (foundFlight.isEmpty()) {
            throw new ResourceNotFoundException("Flight not found with id: " + id);
        }

        return modelMapper.map(foundFlight.get(), FlightResponseDto.class);
    }

    public FlightResponseDto updateFlight(Long id, FlightCreateDto flightCreateDto) {
        Optional<Flight> foundFlight = flightRepository.findById(id);

        if (foundFlight.isEmpty()) {
            throw new ResourceNotFoundException("Flight not found with id: " + id);
        }

        Flight flight = foundFlight.get();
        flight.setDepartureAirportId(flightCreateDto.getDepartureAirportId());
        flight.setArrivalAirportId(flightCreateDto.getArrivalAirportId());
        flight.setDepartureDateTime(flightCreateDto.getDepartureDateTime());
        flight.setReturnDateTime(flightCreateDto.getReturnDateTime());
        flight.setPrice(flightCreateDto.getPrice());
        flight = flightRepository.save(flight);

        return modelMapper.map(flight, FlightResponseDto.class);
    }

    public void deleteFlight(Long id) {
        if (!flightRepository.existsById(id)) {
            throw new ResourceNotFoundException("Flight not found with id: " + id);
        }
        flightRepository.deleteById(id);
    }
}
