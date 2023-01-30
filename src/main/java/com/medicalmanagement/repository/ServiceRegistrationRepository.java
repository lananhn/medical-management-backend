package com.medicalmanagement.repository;

import com.medicalmanagement.entity.ServiceRegistration;
import com.medicalmanagement.services.dto.response.ServiceRegistraitonProjection;
import com.medicalmanagement.services.dto.response.ServiceRegistrationByPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRegistrationRepository extends JpaRepository<ServiceRegistration, Long> {
    @Query(nativeQuery = true, value = "SELECT service_registration.id as regId, service_registration.unit as unit, service_registration.patient_id as patientId, service_registration.service_id as serviceId, service_registration.doctor_id as doctorId, service_registration.quantity as quantity, service_registration.date_created as dateCreated,\n" +
            "patients.full_name as patientName, services.name as serviceName,\n" +
            "users.name as doctorName FROM service_registration\n" +
            "INNER JOIN services on service_registration.service_id=services.id\n" +
            "INNER JOIN patients on service_registration.patient_id=patients.id\n" +
            "INNER JOIN users on service_registration.doctor_id=users.id")
    List<ServiceRegistraitonProjection> listReg();
//    @Query(nativeQuery = true, value = "SELECT service_registration.id as regId, service_registration.unit as unit, service_registration.quantity as quantity, bills.date_created as dateCreated, services.name as serviceName,\n" +
//            "FROM service_registration\n" +
//            "INNER JOIN services ON service_registration.service_id=service.id")
//    List<ServiceRegistrationByPatient> findAllByPatientId(Long patientId);
    List<ServiceRegistration> findAllByPatientId(Long patientId);
}
