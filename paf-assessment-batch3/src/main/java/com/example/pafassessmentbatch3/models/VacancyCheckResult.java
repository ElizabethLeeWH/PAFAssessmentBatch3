package com.example.pafassessmentbatch3.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VacancyCheckResult {
    private boolean success;
    private String errorMessage;

    public static VacancyCheckResult success() {
        return new VacancyCheckResult(true, null);
    }

    public static VacancyCheckResult failure(String errorMessage) {
        return new VacancyCheckResult(false, errorMessage);
    }
}
