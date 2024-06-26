package com.medicalmanagement.services;

import com.medicalmanagement.entity.ResultExamination;
import com.medicalmanagement.entity.ServiceRegistration;
import com.medicalmanagement.entity.User;
import com.medicalmanagement.exceptions.Exception;
import com.medicalmanagement.repository.ResultsExamRepository;
import com.medicalmanagement.repository.ServiceRegistrationRepository;
import com.medicalmanagement.repository.UserRepository;
import com.medicalmanagement.services.dto.request.ResultsExamDTO;
import com.medicalmanagement.services.dto.response.ResultsExamDto;
import com.medicalmanagement.services.dto.response.ResultsExamProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ResultsExamService {
    private final ResultsExamRepository resultsExamRepository;
    private final ServiceRegistrationRepository registrationRepository;
    private final UserRepository userRepository;
    @Transactional
    public List<ResultsExamProjection> listResults() {
        List<ResultsExamProjection> list = resultsExamRepository.listResults();
        return list;
    }
    @Transactional
    public ResultsExamDTO add(ResultsExamDTO dto) {
        ResultExamination resultExamination = new ResultExamination();
        resultExamination.setResult(dto.getResult());
        resultExamination.setDescription(dto.getDescription());
        ServiceRegistration registration = registrationRepository.findById(dto.getServiceReg()).orElseThrow(() ->
                new Exception("Thong tin dang ky chua ton tai"));
        resultExamination.setServiceReg(registration);
        User user = userRepository.findById(dto.getDoctorId()).orElseThrow(() ->
                new Exception("Thong tin bac sy chua ton tai"));
        resultExamination.setUser(user);
        resultExamination.setDateCreated(new Date());
        resultsExamRepository.save(resultExamination);
        return dto;
    }
    @Transactional
    public ResultsExamDTO update(Long id, ResultsExamDTO dto) {
        ResultExamination resultExamination = resultsExamRepository.findById(id).orElseThrow(() ->
                new Exception("Ket qua kham chua ton tai"));
        resultExamination.setResult(dto.getResult());
        resultExamination.setDescription(dto.getDescription());
        ServiceRegistration registration = registrationRepository.findById(dto.getServiceReg()).orElseThrow(() ->
                new Exception("Thong tin dang ky chua ton tai"));
        resultExamination.setServiceReg(registration);
        User user = userRepository.findById(dto.getDoctorId()).orElseThrow(() ->
                new Exception("Thong tin bac sy chua ton tai"));
        resultExamination.setUser(user);
        resultExamination.setDateCreated(new Date());
        resultsExamRepository.save(resultExamination);
        return dto;
    }
    @Transactional
    public ResultsExamDto findResultById(Long id) {
        ResultExamination resultExamination = resultsExamRepository.findById(id).orElseThrow(() ->
                new Exception("Ket qua kham chua ton tai"));
        ResultsExamDto dto = new ResultsExamDto();
        dto.setId(resultExamination.getId());
        dto.setResult(resultExamination.getResult());
        dto.setDescription(resultExamination.getDescription());
        dto.setServiceReg(resultExamination.getServiceReg().getId());
        dto.setDoctorId(resultExamination.getUser().getId());
        return dto;
    }
}
