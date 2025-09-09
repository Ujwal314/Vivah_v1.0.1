package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Rashi")
public class Rashi {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name="rashi_id")
    private int rashiId;

    @Column(name="rashi_name",nullable = false,length = 20)
    private String rashiName;

}
