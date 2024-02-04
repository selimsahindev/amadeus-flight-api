package com.selimsahin.amadeus.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "airports")
@Getter
@Setter
public class Airport extends BaseModel {
    @Column(nullable = false)
    private String city;
}
