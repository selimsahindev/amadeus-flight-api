package com.selimsahin.amadeus.repositories;

import com.selimsahin.amadeus.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FlightRepository extends JpaRepository<Flight, Long>{
}
