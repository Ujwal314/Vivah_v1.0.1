package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "Gotra") // Note: Table names are often lowercase_with_underscores e.g., "gotra"
public class Gotra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gotra_id")
    private Integer gotraId; // Changed from primitive 'int' to wrapper 'Integer'

    @Column(name = "gotra_name", nullable = false, unique = true) // Added unique constraint
    private String gotraName;

    // A robust equals() and hashCode() is crucial for JPA entities
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gotra gotra = (Gotra) o;
        return gotraId != null && Objects.equals(gotraId, gotra.gotraId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}