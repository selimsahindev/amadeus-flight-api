package com.selimsahin.amadeus.services;

import com.selimsahin.amadeus.models.Airport;
import com.selimsahin.amadeus.models.Flight;
import com.selimsahin.amadeus.repositories.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class FlightDataService {
    private final AirportRepository airportRepository;

    private static final String[] airportCities = {
        "New York", "London", "Tokyo", "Paris", "Berlin", "Sydney", "Toronto", "Istanbul", "Dubai", "Singapore",
        "Los Angeles", "Rome", "Barcelona", "Amsterdam", "Moscow", "Beijing", "Shanghai", "Hong Kong", "Seoul", "Mumbai" };

    public List<Flight> fetchMockFlightData() {
        List<Flight> flights = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 100_000; i++) {
            Flight flight = new Flight();

            // Randomly select departure and arrival airports ensuring they are different
            int departureIndex = random.nextInt(airportCities.length);
            int arrivalIndex;
            do {
                arrivalIndex = random.nextInt(airportCities.length);
            } while (arrivalIndex == departureIndex);

            // Assign random from the array of airport cities
            flight.setDepartureAirport(findAirportByCityOrCreate(airportCities[departureIndex]));
            flight.setArrivalAirport(findAirportByCityOrCreate(airportCities[arrivalIndex]));

            // Generate random dates for departure and arrival ensuring logical consistency
            long minDay = LocalDate.now().toEpochDay();
            long maxDay = LocalDate.now().plusMonths(6).toEpochDay();
            long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
            LocalDate departureDate = LocalDate.ofEpochDay(randomDay);
            LocalDate arrivalDate = departureDate.plusDays(random.nextBoolean() ? 1 : 0);

            flight.setDepartureDate(departureDate);
            flight.setArrivalDate(arrivalDate);
            flight.setCreatedAt(LocalDateTime.now());
            flight.setUpdatedAt(LocalDateTime.now());
            flight.setPrice((double) (random.nextInt(90) + 10) * 10);
            flights.add(flight);
        }

        return flights;
    }

    public Airport findAirportByCityOrCreate(String city) {
        return airportRepository.findByCity(city)
            .orElseGet(() -> {
                Airport newAirport = new Airport();
                newAirport.setCity(city);
                airportRepository.save(newAirport);
                return newAirport;
            });
    }
}

