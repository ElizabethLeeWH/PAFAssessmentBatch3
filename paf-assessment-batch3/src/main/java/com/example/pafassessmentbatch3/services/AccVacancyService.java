package com.example.pafassessmentbatch3.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pafassessmentbatch3.models.AccVacancy;
import com.example.pafassessmentbatch3.models.Reservation;
import com.example.pafassessmentbatch3.models.VacancyCheckResult;
import com.example.pafassessmentbatch3.repositories.AccVacancyRepo;

@Service
public class AccVacancyService {
    @Autowired
    private AccVacancyRepo accVRepo;

    public VacancyCheckResult isThereVacancy(Reservation r) {
        Optional<AccVacancy> accVacancyOptional = accVRepo.findAccById(r.getAccId());

        if (accVacancyOptional.isPresent()) {
            AccVacancy accVacancyObj = accVacancyOptional.get();
            Short vacancyAvailable = accVacancyObj.getVacancy();
            System.out.printf("vacancy avail: %d, duration input: %d\n", vacancyAvailable, r.getDuration());

            if (vacancyAvailable >= r.getDuration()) {
                int rowsUpdated = accVRepo.updateVacancy(r.getAccId(), r.getDuration());
                System.out.println(rowsUpdated);
                if (rowsUpdated > 0) {
                    return VacancyCheckResult.success();
                } else {
                    return VacancyCheckResult.failure("Failed to update remaining vacancy");
                }
            } else {
                return VacancyCheckResult.failure("Not enough vacancy for selected duration");
            }
        } else {
            return VacancyCheckResult.failure("Accommodation not found");
        }
    }

}
