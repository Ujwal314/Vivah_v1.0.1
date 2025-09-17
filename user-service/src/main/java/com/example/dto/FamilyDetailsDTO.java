package com.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FamilyDetailsDTO {
    private Long familyId;
    private String fatherName;
    private String motherName;
    private Integer siblingsCount;
    private Double annualIncome;
}
