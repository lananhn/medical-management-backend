package com.medicalmanagement.services;

import com.medicalmanagement.constants.InsuranceStatus;
import com.medicalmanagement.entity.Patient;
import com.medicalmanagement.entity.ServiceEntity;
import com.medicalmanagement.entity.TypeOfService;
import com.medicalmanagement.entity.User;
import com.medicalmanagement.exceptions.Exception;
import com.medicalmanagement.repository.PatientRepository;
import com.medicalmanagement.repository.UserRepository;
import com.medicalmanagement.services.dto.request.PatientDTO;
import com.medicalmanagement.services.dto.response.PatientDto;
import com.medicalmanagement.services.dto.response.PatientProjection;
import com.medicalmanagement.services.dto.response.ServiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientService {
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    public Patient findByIdPatient(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() ->
                new Exception("Thông tin của bệnh nhân chưa tồn tại"));
        return patient;
    }

    @Transactional
    public List<PatientProjection> list() {
        List<PatientProjection> dtoList = patientRepository.listPatients();
        return dtoList;
    }
    @Transactional
    public Patient add(PatientDTO dto) {
        Patient patient = new Patient();
        patient.setFullName(dto.getFullName());
        patient.setGender(dto.getGender());
        patient.setAddress(dto.getAddress());
        patient.setPhone(dto.getPhone());
        patient.setBirthday(dto.getBirthday());
        patient.setInsurance(dto.getInsurance());
        User user = userRepository.findById(dto.getNurseId()).orElseThrow(() ->
                new Exception("Thong tin y ta chua ton tai"));
        patient.setUser(user);
        patient.setDateCreated(new Date());
        patientRepository.save(patient);
        return patient;
    }
    @Transactional
    public Patient update(Long id, PatientDTO dto) {
        Patient patient = findByIdPatient(id);
        patient.setFullName(dto.getFullName());
        patient.setGender(dto.getGender());
        patient.setAddress(dto.getAddress());
        patient.setPhone(dto.getPhone());
        patient.setBirthday(dto.getBirthday());
        patient.setInsurance(dto.getInsurance());
        User user = userRepository.findById(dto.getNurseId()).orElseThrow(() ->
                new Exception("Thong tin y ta chua ton tai"));
        patient.setUser(user);
        patient.setUser(user);
        patient.setDateCreated(new Date());
        patientRepository.save(patient);
        return patient;
    }
    @Transactional
    public void updateInsurance(Long id) {
        Patient patient = findByIdPatient(id);
        if (patient.getInsurance().equals(InsuranceStatus.HAVEINSURANCE.getId())) {
            patient.setInsurance(InsuranceStatus.NOINSURANCE.getId());
            patientRepository.save(patient);
        } else {
            patient.setInsurance(InsuranceStatus.HAVEINSURANCE.getId());
            patientRepository.save(patient);
        }
    }
    @Transactional
    public PatientDto findPatientById(Long id) {
        Patient entity = patientRepository.findById(id).orElseThrow(() ->
                new Exception("Chưa tồn tại thông tin của bệnh nhân"));
        PatientDto dto = new PatientDto();
        dto.setId(entity.getId());
        dto.setFullName(entity.getFullName());
        dto.setGender(entity.getGender());
        dto.setBirthday(entity.getBirthday());
        dto.setAddress(entity.getAddress());
        dto.setPhone(entity.getPhone());
        dto.setInsurance(entity.getInsurance());
        dto.setNurseId(entity.getUser().getId());
        return dto;
    }
}
