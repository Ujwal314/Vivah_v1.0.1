package com.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {
    private Long addressId;
    private String city;
    private String state;
    private String country;
    private String postalCode;
}
