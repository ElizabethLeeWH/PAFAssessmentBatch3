package com.example.pafassessmentbatch3.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.pafassessmentbatch3.models.Reservation;

@Repository
public class ReservationsRepo {
    @Autowired
    private JdbcTemplate template;

    public boolean insertReservation(Reservation r) {
        return template.update(Queries.SQL_INSERT_RESERVATION
              , r.getResvId(), r.getName(), r.getEmail(), r.getAccId(), r.getArrivalDate(), r.getDuration())>0;
    }
}
