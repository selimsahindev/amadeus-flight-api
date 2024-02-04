package com.selimsahin.amadeus.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.selimsahin.amadeus.dtos.FlightResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import com.selimsahin.amadeus.dtos.FlightSearchCriteriaDto;
import com.selimsahin.amadeus.models.Flight;
import com.selimsahin.amadeus.repositories.FlightRepository;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FlightSearchServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FlightSearchService flightSearchService;

    @Test
    public void testSearchFlights() {
        // Given
        FlightSearchCriteriaDto criteria = new FlightSearchCriteriaDto(1L, 2L, LocalDate.of(2024, 2, 20), LocalDate.of(2024, 2, 22));
        Flight departureFlight = new Flight(); // Set properties accordingly
        Flight returnFlight = new Flight(); // Set properties accordingly

        when(flightRepository.findFlightsByCriteria(1L, 2L, LocalDate.of(2024, 2, 20))).thenReturn(List.of(departureFlight));
        when(flightRepository.findFlightsByCriteria(2L, 1L, LocalDate.of(2024, 2, 22))).thenReturn(List.of(returnFlight));

        when(modelMapper.map(any(Flight.class), eq(FlightResponseDto.class))).thenAnswer(invocation -> {
            Flight source = invocation.getArgument(0);
            FlightResponseDto dto = new FlightResponseDto();
            return dto;
        });

        // When
        Iterable<FlightResponseDto> results = flightSearchService.searchFlights(criteria);

        // Then
        assertNotNull(results);
        List<FlightResponseDto> resultList = (List<FlightResponseDto>) results;
        assertEquals(2, resultList.size()); // Assuming one departure and one return flight

        // Verify interactions
        verify(flightRepository).findFlightsByCriteria(1L, 2L, LocalDate.of(2024, 2, 20));
        verify(flightRepository).findFlightsByCriteria(2L, 1L, LocalDate.of(2024, 2, 22));
        verify(modelMapper, times(2)).map(any(Flight.class), eq(FlightResponseDto.class));
    }
}