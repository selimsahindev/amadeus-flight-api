package com.selimsahin.amadeus.services;

import com.selimsahin.amadeus.dtos.FlightCreateDto;
import com.selimsahin.amadeus.dtos.FlightResponseDto;
import com.selimsahin.amadeus.models.Flight;
import com.selimsahin.amadeus.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;

    public FlightResponseDto createFlight(FlightCreateDto flightCreateDto) {
        Flight createdFlight = modelMapper.map(flightCreateDto, Flight.class);
        createdFlight = flightRepository.save(createdFlight);
        // print createdFlight
        System.out.println(createdFlight);
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
            throw new RuntimeException("Flight not found");
        }

        return modelMapper.map(foundFlight.get(), FlightResponseDto.class);
    }

    public FlightResponseDto updateFlight(Long id, FlightCreateDto flightCreateDto) {
        Optional<Flight> foundFlight = flightRepository.findById(id);

        if (foundFlight.isEmpty()) {
            return null;
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
        flightRepository.deleteById(id);
    }
}
