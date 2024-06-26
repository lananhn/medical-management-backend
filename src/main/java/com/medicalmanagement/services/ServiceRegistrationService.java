package com.medicalmanagement.services;

import com.medicalmanagement.entity.*;
import com.medicalmanagement.exceptions.Exception;
import com.medicalmanagement.repository.PatientRepository;
import com.medicalmanagement.repository.ServiceRegistrationRepository;
import com.medicalmanagement.repository.ServiceRepository;
import com.medicalmanagement.repository.UserRepository;
import com.medicalmanagement.services.dto.UpdateRegDTO;
import com.medicalmanagement.services.dto.request.AddRegDTO;
import com.medicalmanagement.services.dto.response.RegistrationDto;
import com.medicalmanagement.services.dto.response.ServiceDto;
import com.medicalmanagement.services.dto.response.ServiceRegistraitonProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceRegistrationService {
    private final ServiceRegistrationRepository registrationRepository;
    private final ServiceRepository serviceRepository;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<ServiceRegistraitonProjection> listReg() {
        List<ServiceRegistraitonProjection> list = registrationRepository.listReg();
        return list;
    }
    @Transactional
    public AddRegDTO add(AddRegDTO dto) {
        Patient patient = new Patient();
        patientRepository.findById(dto.getPatientId()).orElseThrow(() ->
                new Exception("Thong tin benh nhan chua ton tai"));
        patient.setId(dto.getPatientId());
        User user = new User();
        userRepository.findById(dto.getDoctorId()).orElseThrow(() ->
                new Exception("Thong tin bac sy chua ton tai"));
        user.setId(dto.getDoctorId());
        dto.getServiceId().forEach(s -> {
            ServiceRegistration entity = new ServiceRegistration();
            entity.setUnit(dto.getUnit());
            entity.setQuantity(dto.getQuantity());
            ServiceEntity service = new ServiceEntity();
            serviceRepository.findById(s).orElseThrow(() ->
                    new Exception("Dich vu chua ton tai"));
            service.setId(s);
            entity.setService(service);
            entity.setPatient(patient);
            entity.setUser(user);
            entity.setDateCreated(new Date());
            registrationRepository.save(entity);
        });
        return dto;
    }
    @Transactional
    public UpdateRegDTO update(Long id, UpdateRegDTO dto) {
        ServiceRegistration entity = registrationRepository.findById(id).orElseThrow(() ->
                new Exception("Thông tin chưa tồn tại"));
        entity.setUnit(dto.getUnit());
        entity.setQuantity(dto.getQuantity());
        ServiceEntity service = serviceRepository.findById(dto.getServiceId()).orElseThrow(() ->
                new Exception("Dich vu chua ton tai"));
        entity.setService(service);
        Patient patient = patientRepository.findById(dto.getPatientId()).orElseThrow(() ->
                new Exception("Thong tin benh nhan chua ton tai"));
        entity.setPatient(patient);
        User user = userRepository.findById(dto.getDoctorId()).orElseThrow(() ->
                new Exception("Thong tin bac sy chua ton tai"));
        entity.setUser(user);
        entity.setDateCreated(new Date());
        registrationRepository.save(entity);
        return dto;
    }
    @Transactional
    public List<ServiceRegistration> listServiceByPatient(Long patientId) {
        List<ServiceRegistration> list = registrationRepository.findAllByPatientId(patientId);
        return list;
    }
    @Transactional
    public RegistrationDto findRegById(Long id) {
        ServiceRegistration entity = registrationRepository.findById(id).orElseThrow(() ->
                new Exception("Chưa tồn tại thông tin đăng ký"));
        RegistrationDto dto = new RegistrationDto();
        dto.setId(entity.getId());
        dto.setServiceId(entity.getService().getId());
        dto.setPatientId(entity.getPatient().getId());
        dto.setDoctorId(entity.getUser().getId());
        dto.setUnit(entity.getUnit());
        dto.setQuantity(entity.getQuantity());
        return dto;
    }
}
