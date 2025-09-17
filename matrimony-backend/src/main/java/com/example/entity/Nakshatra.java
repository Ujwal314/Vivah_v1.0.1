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
// Conventionally, table names are lowercase and use underscores (e.g., "nakshatra")
@Table(name="Nakshatra")
public class Nakshatra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nakshatra_id")
    private Integer nakshatraId; // Use wrapper 'Integer' instead of primitive 'int'

    @Column(name = "nakshatra_name", nullable = false, unique = true) // Enforce unique names
    private String nakshatraName;

    // A robust equals() and hashCode() is essential for JPA entities
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nakshatra nakshatra = (Nakshatra) o;
        return nakshatraId != null && Objects.equals(nakshatraId, nakshatra.nakshatraId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}