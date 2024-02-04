package com.selimsahin.amadeus.repositories;

import com.selimsahin.amadeus.models.Airport;
import com.selimsahin.amadeus.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.Mapping;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource
public interface FlightRepository extends JpaRepository<Flight, Long>{
    List<Flight> findByDepartureAirportIdAndArrivalAirportId(Long departureAirportId, Long arrivalAirportId);
}
