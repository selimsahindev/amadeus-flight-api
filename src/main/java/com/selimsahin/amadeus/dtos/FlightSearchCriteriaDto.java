package com.selimsahin.amadeus.dtos;

import java.time.LocalDate;
import java.util.Optional;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class FlightSearchCriteriaDto {
    private Long departureAirportId;
    private Long arrivalAirportId;
    private LocalDate departureDate;
    private LocalDate returnDate;

    public FlightSearchCriteriaDto(Long departureAirportId, Long arrivalAirportId, LocalDate departureDate) {
        this.departureAirportId = departureAirportId;
        this.arrivalAirportId = arrivalAirportId;
        this.departureDate = departureDate;
    }

    public FlightSearchCriteriaDto(Long departureAirportId, Long arrivalAirportId, LocalDate departureDate, LocalDate returnDate) {
        this(departureAirportId, arrivalAirportId, departureDate);
        this.returnDate = returnDate;
    }

    public boolean hasReturnDate() {
        return Optional.ofNullable(returnDate).isPresent();
    }
}