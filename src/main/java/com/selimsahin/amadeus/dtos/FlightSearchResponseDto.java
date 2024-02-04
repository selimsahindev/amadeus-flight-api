package com.selimsahin.amadeus.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FlightSearchResponseDto {
    private List<FlightResponseDto> departureFlights;
    private List<FlightResponseDto> returnFlights;
}