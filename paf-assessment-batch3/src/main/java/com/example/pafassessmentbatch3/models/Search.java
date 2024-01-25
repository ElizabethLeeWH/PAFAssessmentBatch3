package com.example.pafassessmentbatch3.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Search {
    @NotEmpty(message="Field cannot be empty")
    @NotNull(message="Field cannot be null")
    private String country;

    @Min(value=1)
    @Max(value=10)
    private Integer accommodates;

    @Min(value = 1)
    @Max(value = 10000)
    private double minPrice;

    @Min(value = 1)
    @Max(value = 10000)
    private double maxPrice;
}
