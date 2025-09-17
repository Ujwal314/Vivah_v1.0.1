package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString(exclude = "religion") // Important: Exclude the relationship field from toString()
@Entity
@Table(name = "caste")
public class Caste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "caste_id") // 1. Use snake_case for column names
    private Integer casteId;     // 2. Use Wrapper type Integer instead of primitive int

    @Column(name = "caste_name", nullable = false) // 1. Use snake_case
    private String casteName;

    @ManyToOne(fetch = FetchType.LAZY) // 3. Be explicit about fetch type
    @JoinColumn(name = "religion_id", nullable = false) // 1. Use snake_case
    private Religion religion;

    // 4. Implement a robust equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caste caste = (Caste) o;
        // Works even for new (transient) entities before they have an ID
        return casteId != null && Objects.equals(casteId, caste.casteId);
    }

    @Override
    public int hashCode() {
        // Use a constant for transient entities to prevent issues with Sets
        return getClass().hashCode();
    }
}