package com.selimsahin.amadeus.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "flights")
@Getter
@Setter
@RequiredArgsConstructor
public class Flight extends BaseModel {
    @ManyToOne
    @JoinColumn(nullable = false, name = "departure_airport_id", referencedColumnName = "id")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(nullable = false, name = "arrival_airport_id", referencedColumnName = "id")
    private Airport arrivalAirport;

    @Column(nullable = false)
    private LocalDate departureDate;

    @Column(nullable = false)
    private LocalDate arrivalDate;

    @Column(nullable = false)
    private Double price;
}
