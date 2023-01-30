package com.medicalmanagement.repository;

import com.medicalmanagement.entity.Patient;
import com.medicalmanagement.services.dto.response.PatientProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query(nativeQuery = true, value = "SELECT patients.id as patientID, patients.address as address, patients.birthday as birthday, patients.full_name as fullName, patients.gender as gender, patients.insurance as insurance, patients.phone as phone, patients.date_created as dateCreated,\n" +
            "users.id as nurseId, users.name as nurseName FROM patients\n" +
            "INNER JOIN users ON users.id=patients.nurse_id")
    List<PatientProjection> listPatients();
}
