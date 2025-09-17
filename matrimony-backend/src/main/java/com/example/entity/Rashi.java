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
@Table(name="Rashi")
public class Rashi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rashi_id")
    private Integer rashiId; // Use wrapper 'Integer' for nullability before saving

    @Column(name="rashi_name", nullable = false, length = 20, unique = true) // Added unique constraint
    private String rashiName;

    // A robust equals() and hashCode() is crucial for JPA entities
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rashi rashi = (Rashi) o;
        return rashiId != null && Objects.equals(rashiId, rashi.rashiId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}