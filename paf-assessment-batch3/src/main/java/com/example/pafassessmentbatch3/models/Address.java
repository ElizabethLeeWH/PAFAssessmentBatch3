package com.example.pafassessmentbatch3.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {
    private String street;
    private String suburb;
    private String governmentArea;
    private String market;
    private String country;
    private String countryCode;
    private Location location;
}
