package com.selimsahin.amadeus.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "airports")
@Data
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String country;
}
