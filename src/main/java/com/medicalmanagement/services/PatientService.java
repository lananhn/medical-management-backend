package com.medicalmanagement.services;

import com.medicalmanagement.constants.InsuranceStatus;
import com.medicalmanagement.entity.Patient;
import com.medicalmanagement.entity.UserEntity;
import com.medicalmanagement.exceptions.Exception;
import com.medicalmanagement.repository.PatientRepository;
import com.medicalmanagement.repository.UserRepository;
import com.medicalmanagement.services.dto.PatientDTO;
import com.medicalmanagement.services.dto.response.PatientProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        UserEntity userEntity = userRepository.findById(dto.getNurseId()).orElseThrow(() ->
                new Exception("Thong tin y ta chua ton tai"));
        patient.setUser(userEntity);
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
        UserEntity userEntity = userRepository.findById(dto.getNurseId()).orElseThrow(() ->
                new Exception("Thong tin y ta chua ton tai"));
        patient.setUser(userEntity);
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
}
