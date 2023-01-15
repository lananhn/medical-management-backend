package com.medicalmanagement.repository;

import com.medicalmanagement.entity.Bill;
import com.medicalmanagement.entity.ServiceRegistration;
import com.medicalmanagement.services.dto.response.BillProjection;
import com.medicalmanagement.services.dto.response.BillSumProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    @Query(nativeQuery = true, value = "SELECT bills.id as billExId, bills.money_received as moneyReceived, bills.health_insurance_payment as insurancePayment, bills.surplus as surplus, bills.status as status, bills.date_created as dateCreated, service_registration.unit as unit, service_registration.quantity as quantity,\n" +
            "patients.full_name as patientName, services.name as serviceName, \n" +
            "users.name as accountantName FROM bills\n" +
            "INNER JOIN service_registration on service_registration.id=bills.service_registration_id\n" +
            "INNER JOIN services on service_registration.service_id=services.id\n" +
            "INNER JOIN patients on service_registration.patient_id=patients.id\n" +
            "INNER JOIN users on service_registration.doctor_id=users.id")
    List<BillProjection> listBillExam();
//    @Query(nativeQuery = true, value = "SELECT ")
//    List<BillSumProjection> findAllByPatientId(Long patientId);

//    List<Bill> findAllByPatientId(Long patientId);
}
