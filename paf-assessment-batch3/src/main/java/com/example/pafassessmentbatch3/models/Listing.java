package com.example.pafassessmentbatch3.models;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


@Data
@NoArgsConstructor
public class Listing {

    @Id
    private String id;

    private String name;
    private String desc;
    private Address address;
    private Integer accommodates;
    private Image imageUrl;
    private List<String> amenities;
    private double price;
    
}
