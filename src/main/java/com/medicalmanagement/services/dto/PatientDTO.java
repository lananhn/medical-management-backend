package com.medicalmanagement.services.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class PatientDTO {
    @NotBlank
    @Size(min = 1, max = 255)
    private String fullName;
    @NotBlank
    @Size(min = 1, max = 30)
    private String gender;
    @JsonFormat(pattern="yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private String birthday;
    @Size(min = 1, max = 255)
    private String address;
    @Size(max = 12)
    private String phone;
    @NotNull
    private Integer insurance;
    private Long nurseId;
}
