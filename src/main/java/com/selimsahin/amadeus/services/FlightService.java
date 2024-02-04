package com.selimsahin.amadeus.services;

import com.selimsahin.amadeus.dtos.FlightCreateDto;
import com.selimsahin.amadeus.dtos.FlightResponseDto;
import com.selimsahin.amadeus.exceptions.ResourceNotFoundException;
import com.selimsahin.amadeus.models.Airport;
import com.selimsahin.amadeus.models.Flight;
import com.selimsahin.amadeus.repositories.AirportRepository;
import com.selimsahin.amadeus.repositories.FlightRepository;
import jakarta.transaction.Transactional;
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

    public FlightResponseDto findFlightById(Long id) {
        Flight foundFlight = getFlightById(id);
        return modelMapper.map(foundFlight, FlightResponseDto.class);
    }

    @Transactional
    public FlightResponseDto updateFlight(Long id, FlightCreateDto flightCreateDto) {
        Flight updatedFlight = getFlightById(id);
        updateFlightFromDto(updatedFlight, flightCreateDto);

        updatedFlight = flightRepository.save(updatedFlight);
        return modelMapper.map(updatedFlight, FlightResponseDto.class);
    }

    public void deleteFlight(Long id) {
        if (!flightRepository.existsById(id)) {
            throw new ResourceNotFoundException("Flight not found with id: " + id);
        }
        flightRepository.deleteById(id);
    }

    private Airport getAirportById(Long id) {
        Optional<Airport> airport = airportRepository.findById(id);
        if (airport.isEmpty()) {
            throw new ResourceNotFoundException("Airport not found with id: " + id);
        }
        return airport.get();
    }

    private void updateFlightFromDto(Flight flight, FlightCreateDto flightCreateDto) {
        if (flightCreateDto.getDepartureAirportId() != null) {
            Airport departureAirport = getAirportById(flightCreateDto.getDepartureAirportId());
            flight.setDepartureAirport(departureAirport);
        }
        if (flightCreateDto.getArrivalAirportId() != null) {
            Airport arrivalAirport = getAirportById(flightCreateDto.getArrivalAirportId());
            flight.setArrivalAirport(arrivalAirport);
        }
        if (flightCreateDto.getDepartureDate() != null) {
            flight.setDepartureDate(flightCreateDto.getDepartureDate());
        }
        if (flightCreateDto.getArrivalDate() != null) {
            flight.setArrivalDate(flightCreateDto.getArrivalDate());
        }
        if (flightCreateDto.getPrice() != null) {
            flight.setPrice(flightCreateDto.getPrice());
        }

        flight.setUpdatedAt(LocalDateTime.now());
    }

    private Flight getFlightById(Long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        if (flight.isEmpty()) {
            throw new ResourceNotFoundException("Flight not found with id: " + id);
        }
        return flight.get();
    }
}
