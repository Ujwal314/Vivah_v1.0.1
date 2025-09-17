package com.example.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import lombok.*;
import org.springframework.http.HttpStatus;


import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "Religion")
public class Religion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer religionId; // Use wrapper 'Integer' instead of primitive 'int'

    @Column(name="religion_name", nullable = false, unique = true, length = 50)
    private String religionName;


    // A robust equals() and hashCode() is crucial for JPA entities
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Religion religion = (Religion) o;
        return religionId != null && Objects.equals(religionId, religion.religionId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

    public HttpStatus getStatusCode() {
        return HttpStatus.OK;
    }
}

