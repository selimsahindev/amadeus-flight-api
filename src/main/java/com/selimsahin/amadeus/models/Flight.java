package com.selimsahin.amadeus.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
@Getter
@Setter
@RequiredArgsConstructor
public class Flight extends BaseModel {
    @ManyToOne
    @JoinColumn(nullable = false, name = "departure_airport_id", referencedColumnName = "id")
    private Airport departureAirportId;

    @ManyToOne
    @JoinColumn(nullable = false, name = "arrival_airport_id", referencedColumnName = "id")
    private Airport arrivalAirportId;

    @Column(nullable = false)
    private LocalDateTime departureDateTime;

    @Column(nullable = false)
    private LocalDateTime returnDateTime;

    @Column(nullable = false)
    private Double price;
}
