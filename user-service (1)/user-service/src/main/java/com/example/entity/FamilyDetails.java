package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Family_Details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FamilyDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long familyId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String fatherName;
    private String motherName;
    private Integer siblingsCount;
    private Double annualIncome;
}

