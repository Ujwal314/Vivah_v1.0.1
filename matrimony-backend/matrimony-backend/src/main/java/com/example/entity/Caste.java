package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "caste")
public class Caste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int casteId;

    @Column(nullable = false)
    private String casteName;

    @ManyToOne
    @JoinColumn(name = "religionId", nullable = false)
    private Religion religion;

}
