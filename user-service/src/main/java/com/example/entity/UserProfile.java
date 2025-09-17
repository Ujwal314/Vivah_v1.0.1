package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "User_Profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    @JsonIgnore
    private User user;

    private Integer age;
    private Double height;
    private Double weight;
    private Double salaryPackage;
    private String jobLocation;
    private String education;
    private String occupation;
    private Boolean mangalik;
    private Boolean disability;
    private String disablityType;
    private String bloodGroup;
    private Boolean gender;
    private String maritalStatus;
    private Integer rashiId;
    private Integer nakshatraId;
    private Integer gotraId;
    private Integer paada;
    private Integer casteId;
    private Integer subcasteId;
    private Integer religionId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "family_id", referencedColumnName = "familyId")
    private FamilyDetails familyDetails;
}
