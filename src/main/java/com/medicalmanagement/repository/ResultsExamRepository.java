package com.medicalmanagement.repository;

import com.medicalmanagement.entity.ResultExamination;
import com.medicalmanagement.services.dto.response.ResultsExamProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultsExamRepository extends JpaRepository<ResultExamination, Long> {
    @Query(nativeQuery = true, value = "SELECT results_examination.id as resultsId, results_examination.date_created as dateCreated, results_examination.result as result, results_examination.description as description,\n" +
            "services.name as serviceName, patients.full_name as patientName, users.name as doctorName\n" +
            "FROM results_examination\n" +
            "INNER JOIN service_registration ON service_registration.id=results_examination.service_registration_id\n" +
            "INNER JOIN services ON service_registration.service_id=services.id\n"+
            "INNER JOIN patients ON patients.id=service_registration.patient_id\n" +
            "INNER JOIN users ON users.id=results_examination.doctor_id")
    List<ResultsExamProjection> listResults();
}
