package com.selimsahin.amadeus.services;

import com.selimsahin.amadeus.dtos.FlightCreateDto;
import com.selimsahin.amadeus.dtos.FlightResponseDto;
import com.selimsahin.amadeus.exceptions.ResourceNotFoundException;
import com.selimsahin.amadeus.models.Airport;
import com.selimsahin.amadeus.models.Flight;
import com.selimsahin.amadeus.repositories.AirportRepository;
import com.selimsahin.amadeus.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final ModelMapper modelMapper;

    public FlightResponseDto createFlight(FlightCreateDto flightCreateDto) {
        Flight createdFlight = modelMapper.map(flightCreateDto, Flight.class);
        createdFlight = flightRepository.save(createdFlight);
        return modelMapper.map(createdFlight, FlightResponseDto.class);
    }

    public Iterable<FlightResponseDto> getFlights() {
        return flightRepository
                .findAll()
                .stream()
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
        Optional<Flight> flight = flightRepository.findById(id);
        if (flight.isEmpty()) {
            throw new ResourceNotFoundException("Flight not found with id: " + id);
        }
        Flight updatedFlight = flight.get();

        if (flightCreateDto.getDepartureAirportId() != null) {
            Optional<Airport> departureAirport = airportRepository.findById(flightCreateDto.getDepartureAirportId());
            if (departureAirport.isEmpty()) {
                throw new ResourceNotFoundException("Departure airport not found with id: " + flightCreateDto.getDepartureAirportId());
            }
            updatedFlight.setDepartureAirport(departureAirport.get());
        }
        if (flightCreateDto.getArrivalAirportId() != null) {
            Optional<Airport> arrivalAirport = airportRepository.findById(flightCreateDto.getArrivalAirportId());
            if (arrivalAirport.isEmpty()) {
                throw new ResourceNotFoundException("Arrival airport not found with id: " + flightCreateDto.getArrivalAirportId());
            }
            updatedFlight.setArrivalAirport(arrivalAirport.get());
        }
        if (flightCreateDto.getDepartureDateTime() != null) {
            updatedFlight.setDepartureDateTime(flightCreateDto.getDepartureDateTime());
        }
        if (flightCreateDto.getArrivalDateTime() != null) {
            updatedFlight.setArrivalDateTime(flightCreateDto.getArrivalDateTime());
        }
        if (flightCreateDto.getPrice() != null) {
            updatedFlight.setPrice(flightCreateDto.getPrice());
        }

        updatedFlight.setCreatedAt(updatedFlight.getCreatedAt());
        updatedFlight.setUpdatedAt(LocalDateTime.now());

        updatedFlight = flightRepository.save(updatedFlight);
        return modelMapper.map(updatedFlight, FlightResponseDto.class);
    }

    public void deleteFlight(Long id) {
        if (!flightRepository.existsById(id)) {
            throw new ResourceNotFoundException("Flight not found with id: " + id);
        }
        flightRepository.deleteById(id);
    }
}
