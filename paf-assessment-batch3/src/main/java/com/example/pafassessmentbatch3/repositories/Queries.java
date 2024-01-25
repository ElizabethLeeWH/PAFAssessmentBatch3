package com.example.pafassessmentbatch3.repositories;

public class Queries {
    public static final String SQL_INSERT_RESERVATION = """
            insert into reservations(resv_id, name, email, acc_id, arrival_date, duration)
                values (?, ?, ?, ?, ?, ?)
            """;

    public static final String SQL_SELECT_VACANCY_BY_ACCID = """
               select acc_id, vacancy
                  from acc_occupancy
                  where acc_id = ?
            """;

    public static final String SQL_UPDATE_VACANCY = """
            UPDATE acc_occupancy
            SET vacancy = vacancy - ? 
            WHERE acc_id = ?
                """;
}
