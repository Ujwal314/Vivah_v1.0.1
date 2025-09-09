package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Gotra")
public class Gotra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gotra_id")
    private int gotraId;

    @Column(name = "gotra_name", nullable = false)
    private String gotraName;
}
