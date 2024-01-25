package com.example.pafassessmentbatch3.models;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Reservation {
    private String resvId;
    private String name;
    private String email;
    private String accId;
    private Date arrivalDate;
    private Short duration;
}
