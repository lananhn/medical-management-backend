package com.medicalmanagement.services;

import com.medicalmanagement.constants.Status;
import com.medicalmanagement.entity.Bill;
import com.medicalmanagement.entity.ServiceRegistration;
import com.medicalmanagement.entity.UserEntity;
import com.medicalmanagement.exceptions.Exception;
import com.medicalmanagement.repository.BillRepository;
import com.medicalmanagement.repository.ServiceRegistrationRepository;
import com.medicalmanagement.repository.UserRepository;
import com.medicalmanagement.services.dto.BillDTO;
import com.medicalmanagement.services.dto.BillSumDTO;
import com.medicalmanagement.services.dto.response.BillProjection;
import com.medicalmanagement.services.dto.response.BillSumProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BillService {
    private final BillRepository billExamRepository;
    private final ServiceRegistrationRepository registrationRepository;
    private final UserRepository userRepository;
    @Transactional
    public List<BillProjection> list() {
        List<BillProjection> list = billExamRepository.listBillExam();
        return list;
    }
    @Transactional
    public BillDTO add(BillDTO dto) {
        Bill entity = new Bill();
        entity.setMoneyReceived(dto.getMoneyReceived());
        entity.setDateCreated(new Date());
        ServiceRegistration registration = registrationRepository.findById(dto.getServiceReg()).orElseThrow(() ->
                new Exception("Chua ton tai thong tin dang ky"));
        entity.setServiceReg(registration);
        UserEntity userEntity = userRepository.findById(dto.getAccountantId()).orElseThrow(() ->
                new Exception("Thong tin ke toan chua ton tai"));
        entity.setAccountantId(userEntity);
        billExamRepository.save(entity);
        return dto;
    }
    @Transactional
    public void update(Long id, BillDTO dto) {
        Bill bill = billExamRepository.findById(id).orElseThrow(() ->
                new Exception("Thong tin ban ke chua ton tai"));
        bill.setMoneyReceived(dto.getMoneyReceived());
        ServiceRegistration registration = registrationRepository.findById(dto.getServiceReg()).orElseThrow(() ->
                new Exception("Chua ton tai thong tin dang ky"));
        bill.setServiceReg(registration);
        UserEntity userEntity = userRepository.findById(dto.getAccountantId()).orElseThrow(() ->
                new Exception("Thong tin ke toan chua ton tai"));
        bill.setAccountantId(userEntity);
        bill.setDateCreated(new Date());
        billExamRepository.save(bill);
    }
//    @Transactional
//    public List<Bill> listBillSum(Long patientId) {
//        List<Bill> list = billExamRepository.findAllByPatientId(patientId);
//        return list;
//    }
    @Transactional
    public void updateBillSum(Long id, BillSumDTO dto) {
        Bill bill = billExamRepository.findById(id).orElseThrow(() ->
                new Exception("Thong tin ban ke chua ton tai"));
        bill.setHealthInsurance(dto.getHealthInsurance());
        bill.setSurplus(bill.getMoneyReceived() - dto.getHealthInsurance());
        billExamRepository.save(bill);
    }
    @Transactional
    public void updateStatusMoney(Long id) {
        Bill bill = billExamRepository.findById(id).orElseThrow(() ->
                new Exception("Thong tin ban ke chua ton tai"));
        if (bill.getStatus().equals(Status.ACTIVE.getId())) {
            bill.setStatus(Status.INACTIVE.getId());
            billExamRepository.save(bill);
        } else {
            bill.setStatus(Status.ACTIVE.getId());
            billExamRepository.save(bill);
        }
    }
}
