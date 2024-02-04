package com.selimsahin.amadeus.repositories;

import com.selimsahin.amadeus.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource
public interface FlightRepository extends JpaRepository<Flight, Long>{
    @Query("SELECT f FROM Flight f WHERE " +
            "f.departureAirport.id = :departureAirportId AND " +
            "f.arrivalAirport.id = :arrivalAirportId AND " +
            "f.departureDate >= :departureDate")
    List<Flight> findFlightsByCriteria(@Param("departureAirportId") Long departureAirportId,
                                       @Param("arrivalAirportId") Long arrivalAirportId,
                                       @Param("departureDate") LocalDate departureDate);
}
