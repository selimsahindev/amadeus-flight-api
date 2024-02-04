package com.selimsahin.amadeus.dtos;

import java.time.LocalDate;
import java.util.Optional;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightSearchCriteriaDto {
    @Column(nullable = false)
    private Long departureAirportId;

    @Column(nullable = false)
    private Long arrivalAirportId;

    private LocalDate departureDateTime;
    private LocalDate arrivalDateTime;
    private Optional<LocalDate> returnDateTime;

    public FlightSearchCriteriaDto(Long departureAirportId, Long arrivalAirportId) {
        this.departureAirportId = departureAirportId;
        this.arrivalAirportId = arrivalAirportId;
    }
}