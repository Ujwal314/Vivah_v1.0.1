package com.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.HttpStatus;

@Entity
@Table(name = "Religion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Religion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int religionId;

    @Column(name="religion_name",nullable = false, unique = true, length = 50)
    private String religionName;

    public HttpStatus getStatusCode() {
        return HttpStatus.OK;
    }
}
