package com.selimsahin.amadeus.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    protected LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    protected LocalDateTime updatedAt;
}
