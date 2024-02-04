package com.selimsahin.amadeus.controllers;

import com.selimsahin.amadeus.dtos.FlightResponseDto;
import com.selimsahin.amadeus.dtos.FlightSearchCriteriaDto;
import com.selimsahin.amadeus.dtos.FlightSearchResponseDto;
import com.selimsahin.amadeus.services.FlightSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/flights/search")
@RequiredArgsConstructor
public class FlightSearchController {
    private final FlightSearchService flightSearchService;

    @GetMapping
    public FlightSearchResponseDto searchFlights(@ModelAttribute FlightSearchCriteriaDto searchCriteria) {
        return flightSearchService.searchFlights(searchCriteria);
    }
}
