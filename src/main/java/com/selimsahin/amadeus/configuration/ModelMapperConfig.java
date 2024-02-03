package com.selimsahin.amadeus.configuration;

import com.selimsahin.amadeus.dtos.FlightCreateDto;
import com.selimsahin.amadeus.exceptions.ResourceNotFoundException;
import com.selimsahin.amadeus.models.Airport;
import com.selimsahin.amadeus.models.Flight;
import com.selimsahin.amadeus.repositories.AirportRepository;
import org.modelmapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(AirportRepository airportRepository) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        // Custom mapping from FlightCreateDto to Flight
        modelMapper.addMappings(new PropertyMap<FlightCreateDto, Flight>() {
            @Override
            protected void configure() {
                using(ctx -> airportRepository.findById((Long) ctx.getSource())
                        .orElseThrow(() -> new ResourceNotFoundException("Departure airport not found with id: " + ctx.getSource())))
                        .map(source.getDepartureAirportId(), destination.getDepartureAirport());
                using(ctx -> airportRepository.findById((Long) ctx.getSource())
                        .orElseThrow(() -> new ResourceNotFoundException("Arrival airport not found with id: " + ctx.getSource())))
                        .map(source.getArrivalAirportId(), destination.getArrivalAirport());
            }
        });

        // Custom mapping from Flight to FlightCreateDto
        modelMapper.addMappings(new PropertyMap<Flight, FlightCreateDto>() {
            @Override
            protected void configure() {
                map().setDepartureAirportId(source.getDepartureAirport().getId());
                map().setArrivalAirportId(source.getArrivalAirport().getId());
            }
        });

        return modelMapper;
    }
}
