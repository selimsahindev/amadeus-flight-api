package com.selimsahin.amadeus.repositories;

import com.selimsahin.amadeus.models.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface AirportRepository extends JpaRepository<Airport, Long> {
    Optional<Airport> findByCity(String city);
}
