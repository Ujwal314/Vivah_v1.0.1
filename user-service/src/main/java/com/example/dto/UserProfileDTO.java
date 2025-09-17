package com.example.dto; // Or your preferred DTO package

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDTO {

    private Long profileId;

    // This is the CRUCIAL field. We will manually populate it.
    private Long userId;
    private String fname;
    private String lname;
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

    private String fatherName;
    private String motherName;
    private Integer siblingsCount;
    private Double annualIncome;

    private String city;
    private String state;
    private String country;
    private String postalCode;

}