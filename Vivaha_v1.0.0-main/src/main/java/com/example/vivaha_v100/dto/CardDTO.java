package com.example.vivaha_v100.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    private String fname;
    private String lname;
    private Integer age;
    private boolean gender;
    private String maritalStatus;
    private Double height;
    private Double weight;
    private Double salaryPackage;
    private String jobLocation;
    private String education;
    private String occupation;
    private boolean mangalik;
    private boolean disability;
    private String disabilityType;
    private String bloodGroup;
    private String rashiName;
    private String nakshatraName;
    private String gotraName;
    private Integer paada;
    private String casteName;
    private String religionName;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String fatherName;
    private String motherName;
    private Integer siblingsCount;
    private Double annualIncome;
}
