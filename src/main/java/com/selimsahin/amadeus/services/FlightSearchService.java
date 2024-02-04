package com.selimsahin.amadeus.services;

import com.selimsahin.amadeus.dtos.FlightResponseDto;
import com.selimsahin.amadeus.dtos.FlightSearchCriteriaDto;
import com.selimsahin.amadeus.dtos.FlightSearchResponseDto;
import com.selimsahin.amadeus.exceptions.InvalidArgumentException;
import com.selimsahin.amadeus.models.Flight;
import com.selimsahin.amadeus.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightSearchService {
    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;

    public FlightSearchResponseDto searchFlights(FlightSearchCriteriaDto searchCriteria) {
        // Validate the search criteria if needed.
        validateSearchCriteria(searchCriteria);

        // Extract data from the search criteria.
        Long departureAirportId = searchCriteria.getDepartureAirportId();
        Long arrivalAirportId = searchCriteria.getArrivalAirportId();
        LocalDate departureDate = searchCriteria.getDepartureDate();

        // Search for outbound flights.
        List<Flight> departureFlights = findFlights(departureAirportId, arrivalAirportId, departureDate);

        // Handle return flights if return date is provided.
        List<Flight> returnFlights = null;
        if (searchCriteria.hasReturnDate()) {
            LocalDate returnDate = searchCriteria.getReturnDate();
            returnFlights = findFlights(arrivalAirportId, departureAirportId, returnDate);
        }

        List<FlightResponseDto> departureFlightDtos = mapFlightsToDtos(departureFlights);
        List<FlightResponseDto> returnFlightDtos = mapFlightsToDtos(returnFlights);

        return new FlightSearchResponseDto(departureFlightDtos, returnFlightDtos);
    }

    private List<FlightResponseDto> mapFlightsToDtos(List<Flight> flights) {
        if (flights == null) {
            return null;
        }

        List<FlightResponseDto> flightDtos = new ArrayList<>();
        for (Flight flight : flights) {
            FlightResponseDto flightDto = modelMapper.map(flight, FlightResponseDto.class);
            flightDtos.add(flightDto);
        }
        return flightDtos;
    }

    private void validateSearchCriteria(FlightSearchCriteriaDto searchCriteria) {
        // Check for required fields
        if (searchCriteria.getDepartureAirportId() == null) {
            throw new InvalidArgumentException("Departure airport ID is required.");
        }
        if (searchCriteria.getArrivalAirportId() == null) {
            throw new InvalidArgumentException("Arrival airport ID is required.");
        }
        if (searchCriteria.getDepartureDate() == null) {
            throw new InvalidArgumentException("Departure date is required.");
        }

        // Check for optional fields if needed
        if (searchCriteria.hasReturnDate()) {
            if (searchCriteria.getReturnDate() == null) {
                throw new InvalidArgumentException("Return date can not be null.");
            }
        }
    }

    private List<Flight> findFlights(Long departureAirportId, Long arrivalAirportId, LocalDate departureDate) {
        return flightRepository.findFlightsByCriteria(departureAirportId, arrivalAirportId, departureDate);
    }

    private List<Flight> combineFlights(List<Flight> outboundFlights, List<Flight> returnFlights) {
        if (returnFlights == null) {
            return outboundFlights;
        }

        List<Flight> allFlights = new ArrayList<>(outboundFlights);
        allFlights.addAll(returnFlights);
        return allFlights;
    }
}
