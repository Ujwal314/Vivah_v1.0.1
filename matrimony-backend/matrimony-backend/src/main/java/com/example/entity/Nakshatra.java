package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Nakshatra")
public class Nakshatra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nakshatra_id")
    private int nakshatraId;

    @Column(name = "nakshatra_name", nullable = false)
    private String nakshatraName;

}
