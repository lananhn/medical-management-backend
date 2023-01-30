package com.medicalmanagement.services.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ResultsExamDTO {
    @NotBlank
    @Size(min = 1, max = 255)
    private String result;
    @NotBlank
    @Size(min = 1, max = 255)
    private String description;
    private Long serviceReg;
    private Long doctorId;
}
