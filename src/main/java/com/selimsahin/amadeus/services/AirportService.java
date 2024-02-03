package com.selimsahin.amadeus.services;

import com.selimsahin.amadeus.dtos.AirportCreateDto;
import com.selimsahin.amadeus.dtos.AirportResponseDto;
import com.selimsahin.amadeus.exceptions.ResourceNotFoundException;
import com.selimsahin.amadeus.models.Airport;
import com.selimsahin.amadeus.repositories.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;
    private final ModelMapper modelMapper;

    public AirportResponseDto createAirport(AirportCreateDto airportCreateDto) {
        Airport airport = modelMapper.map(airportCreateDto, Airport.class);
        airport = airportRepository.save(airport);
        return modelMapper.map(airport, AirportResponseDto.class);
    }

    public Iterable<AirportResponseDto> getAirports() {
        return airportRepository
                .findAll()
                .stream()
                .map(airport -> modelMapper.map(airport, AirportResponseDto.class))
                .toList();
    }

    public AirportResponseDto getAirportById(Long id) {
        Optional<Airport> foundAirport = airportRepository.findById(id);

        if (foundAirport.isEmpty()) {
            throw new ResourceNotFoundException("Airport not found with id: " + id);
        }

        return modelMapper.map(foundAirport, AirportResponseDto.class);
    }

    public AirportResponseDto updateAirport(Long id, AirportCreateDto airportCreateDto) {
        Optional<Airport> foundAirport = airportRepository.findById(id);

        if (foundAirport.isEmpty()) {
            throw new ResourceNotFoundException("Airport not found with id: " + id);
        }

        Airport airport = foundAirport.get();
        airport.setCity(airportCreateDto.getCity());
        airport = airportRepository.save(airport);

        return modelMapper.map(airport, AirportResponseDto.class);
    }

    public void deleteAirport(Long id) {
        if (!airportRepository.existsById(id)) {
            throw new ResourceNotFoundException("Airport not found with id: " + id);
        }
        airportRepository.deleteById(id);
    }
}
