package com.medicalmanagement.services.dto.response;

import lombok.Data;

@Data
public class ResultsExamDto {
    private Long id;
    private String result;
    private String description;
    private Long serviceReg;
    private Long doctorId;
}
