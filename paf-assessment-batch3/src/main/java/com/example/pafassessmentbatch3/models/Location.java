package com.example.pafassessmentbatch3.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
public class Location {
    private String type;
    private List<Double> coordinates;
    private boolean isLocationExact;
}
