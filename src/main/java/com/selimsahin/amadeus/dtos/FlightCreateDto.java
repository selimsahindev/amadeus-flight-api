package com.selimsahin.amadeus.dtos;

import com.selimsahin.amadeus.models.Airport;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FlightCreateDto {
    @Column(nullable = false)
    private Airport departureAirportId;

    @Column(nullable = false)
    private Airport arrivalAirportId;

    @Column(nullable = false)
    private LocalDateTime departureDateTime;

    @Column(nullable = false)
    private LocalDateTime returnDateTime;

    @Column(nullable = false)
    private Double price;
}
