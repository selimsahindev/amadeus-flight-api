package com.selimsahin.amadeus.dtos;

import com.selimsahin.amadeus.models.Airport;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
public class FlightResponseDto {
    private Long id;

    private Airport departureAirportId;
    private Airport arrivalAirportId;
    private LocalDateTime departureDateTime;
    private LocalDateTime returnDateTime;
    private Double price;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
