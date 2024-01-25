package com.example.pafassessmentbatch3;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.example.pafassessmentbatch3.models.AccVacancy;

public class Utils {

    public static AccVacancy toAccVacancy(SqlRowSet rs) {
        AccVacancy accVacancy = new AccVacancy();
        accVacancy.setAccId(rs.getString("acc_id"));
        accVacancy.setVacancy(rs.getShort("vacancy"));
        return accVacancy;
    }
}
