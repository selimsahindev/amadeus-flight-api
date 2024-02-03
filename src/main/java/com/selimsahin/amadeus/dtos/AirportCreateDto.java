package com.selimsahin.amadeus.dtos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirportCreateDto {
    @Column(nullable = false)
    private String city;
}
