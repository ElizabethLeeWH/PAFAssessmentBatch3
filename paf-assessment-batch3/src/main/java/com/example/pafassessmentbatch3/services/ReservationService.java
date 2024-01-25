package com.example.pafassessmentbatch3.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pafassessmentbatch3.models.Reservation;
import com.example.pafassessmentbatch3.repositories.ReservationsRepo;

@Service
public class ReservationService {
    @Autowired
    private ReservationsRepo rsvRepo;

    public Reservation createResvId(Reservation r){
        UUID uuid = UUID.randomUUID();
        String shortUuid = uuid.toString().replace("-", "").substring(0, 8);
        r.setResvId(shortUuid);
        return r;
    }

    public boolean insertNewReservationSQL(Reservation r){
        if(!r.getResvId().isEmpty() && r.getAccId()!=null){
            return rsvRepo.insertReservation(r);
        } else {
            return false;
        }
    }
}
