package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

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
}
