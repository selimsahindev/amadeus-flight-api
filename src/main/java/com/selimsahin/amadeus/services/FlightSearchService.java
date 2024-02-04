package com.selimsahin.amadeus.services;

import com.selimsahin.amadeus.dtos.FlightResponseDto;
import com.selimsahin.amadeus.dtos.FlightSearchCriteriaDto;
import com.selimsahin.amadeus.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightSearchService {
    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;

    public Iterable<FlightResponseDto> searchFlights(FlightSearchCriteriaDto request) {
        System.out.println("searchFlights 1");
        Long departureAirportId = request.getDepartureAirportId();
        Long arrivalAirportId = request.getArrivalAirportId();
        System.out.println("searchFlights 2");

        return flightRepository.findByDepartureAirportIdAndArrivalAirportId(departureAirportId, arrivalAirportId)
                .stream()
                .map(flight -> modelMapper.map(flight, FlightResponseDto.class))
                .toList();
    }
}
