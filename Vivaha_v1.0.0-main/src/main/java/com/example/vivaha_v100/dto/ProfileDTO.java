package com.example.vivaha_v100.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private Integer profileId;
    private Integer userId;
    private Integer age;
    private boolean gender;
    private String maritalStatus;
    private Double height;
    private Double weight;
    private Double salaryPackage;
    private String jobLocation;
    private String education;
    private String occupation;
    private Boolean mangalik;
    private Boolean disability;
    private String disabilityType;
    private String bloodGroup;
    private Integer rashiId;
    private Integer nakshatraId;
    private Integer gotraId;
    private Integer paada;
    private Integer casteId;
    private Integer religionId;
}
