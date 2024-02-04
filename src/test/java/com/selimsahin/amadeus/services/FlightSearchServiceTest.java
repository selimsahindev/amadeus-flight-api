package com.selimsahin.amadeus.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.selimsahin.amadeus.dtos.FlightResponseDto;
import com.selimsahin.amadeus.repositories.AirportRepository;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class FlightSearchServiceTest {
    @Mock
    private FlightRepository flightRepository;

    @Mock
    private AirportRepository airportRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FlightSearchService flightSearchService;

    @Test
    public void testSearchFlights() {
        // Given a search criteria:
        FlightSearchCriteriaDto criteria = new FlightSearchCriteriaDto(1L, 2L, LocalDate.of(2024, 2, 20), LocalDate.of(2024, 2, 24));

        // Create a flight for departure.
        Flight departureFlight = new Flight();
        departureFlight.setDepartureDate(LocalDate.of(2024, 2, 20));
        departureFlight.setArrivalDate(LocalDate.of(2024, 2, 22));
        departureFlight.setPrice(140.0);

        // Create a return flight.
        Flight returnFlight = new Flight();
        returnFlight.setDepartureDate(LocalDate.of(2024, 2, 24));
        returnFlight.setArrivalDate(LocalDate.of(2024, 2, 26));
        returnFlight.setPrice(160.0);

        // Mocking repository responses
        when(flightRepository.findFlightsByCriteria(1L, 2L, LocalDate.of(2024, 2, 20))).thenReturn(List.of(departureFlight));
        when(flightRepository.findFlightsByCriteria(2L, 1L, LocalDate.of(2024, 2, 24))).thenReturn(List.of(returnFlight));

        // Mocking ModelMapper behavior
        when(modelMapper.map(any(Flight.class), eq(FlightResponseDto.class))).thenAnswer(invocation -> {
            Flight source = invocation.getArgument(0);
            FlightResponseDto dto = new FlightResponseDto();
            // Assuming a simple mapping for demonstration purposes
            dto.setDepartureDate(source.getDepartureDate());
            dto.setArrivalDate(source.getArrivalDate());
            dto.setPrice(source.getPrice());
            return dto;
        });

        // When
        Iterable<FlightResponseDto> results = flightSearchService.searchFlights(criteria);

        // Then
        assertNotNull(results);
        List<FlightResponseDto> resultList = (List<FlightResponseDto>) results;
        assertEquals(2, resultList.size()); // Assuming one departure and one return flight

        // Asserting the properties of the first result as an example
        FlightResponseDto firstResult = resultList.get(0);
        assertEquals(LocalDate.of(2024, 2, 20), firstResult.getDepartureDate());
        assertEquals(140.0, firstResult.getPrice());

        // Verify interactions
        verify(flightRepository).findFlightsByCriteria(1L, 2L, LocalDate.of(2024, 2, 20));
        verify(flightRepository).findFlightsByCriteria(2L, 1L, LocalDate.of(2024, 2, 24));
        verify(modelMapper, times(2)).map(any(Flight.class), eq(FlightResponseDto.class));
    }
}