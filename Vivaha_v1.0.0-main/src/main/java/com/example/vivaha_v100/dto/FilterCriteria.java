package com.example.vivaha_v100.dto;

import lombok.Data;

@Data
public class FilterCriteria {
    private String maritalStatus;
    private Integer minAge;
    private Integer maxAge;
    private Double minSalary;
    private Double maxSalary;
    private Double minHeight;
    private Double maxHeight;
    private Integer gotraId;
    private Integer casteId;
    private Integer religionId;
    private Boolean mangalik;
    private Boolean disability;
}
