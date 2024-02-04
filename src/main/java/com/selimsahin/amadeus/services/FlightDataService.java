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
    // Example list of airports (Ensure these exist in your database or are replaced with those that do)
    private static final String[] airportCities = {"New York", "London", "Tokyo", "Paris", "Berlin", "Sydney", "Toronto", "Istanbul", "Dubai", "Singapore"};

    public List<Flight> fetchMockFlightData() {
        List<Flight> flights = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            Flight flight = new Flight();

            // Randomly select departure and arrival airports ensuring they are different
            int departureIndex = random.nextInt(airportCities.length);
            int arrivalIndex = departureIndex;
            while (arrivalIndex == departureIndex) {
                arrivalIndex = random.nextInt(airportCities.length);
            }

            flight.setDepartureAirport(findAirportByCityOrCreate(airportCities[departureIndex]));
            flight.setArrivalAirport(findAirportByCityOrCreate(airportCities[arrivalIndex]));

            // Generate random dates for departure and arrival ensuring logical consistency
            long minDay = LocalDate.now().toEpochDay();
            long maxDay = LocalDate.now().plusMonths(6).toEpochDay(); // Assuming flights are within the next 6 months
            long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
            LocalDate departureDate = LocalDate.ofEpochDay(randomDay);
            LocalDate arrivalDate = departureDate.plusDays(random.nextInt(2) + 1); // Arrival is 1-2 days after departure

            flight.setDepartureDate(departureDate);
            flight.setArrivalDate(arrivalDate);
            flight.setCreatedAt(LocalDateTime.now());
            flight.setUpdatedAt(LocalDateTime.now());

            // Set a random price
            double price = 100 + (1000 - 100) * random.nextInt(); // Random price between 100 and 1000
            flight.setPrice(price);

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

