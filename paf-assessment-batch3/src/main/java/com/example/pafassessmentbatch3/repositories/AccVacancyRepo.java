package com.example.pafassessmentbatch3.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.example.pafassessmentbatch3.Utils;
import com.example.pafassessmentbatch3.models.AccVacancy;

@Repository
public class AccVacancyRepo {
    @Autowired
    private JdbcTemplate template;

    public Optional<AccVacancy> findAccById(String accId) {
        Integer accIdInt = Integer.parseInt(accId);
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_VACANCY_BY_ACCID, accIdInt);
        if (!rs.next()){
            return Optional.empty();
        }
        return Optional.of(Utils.toAccVacancy(rs));
    }

    public int updateVacancy(String accId, Short decreaseBy){
        Integer accIdInt = Integer.parseInt(accId);
        return template.update(Queries.SQL_UPDATE_VACANCY, decreaseBy, accIdInt);
    }

}
