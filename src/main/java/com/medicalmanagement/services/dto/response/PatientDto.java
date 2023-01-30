package com.medicalmanagement.services.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {
    private Long id;
    private String fullName;
    private String gender;
    private String birthday;
    private String address;
    private String phone;
    private Integer insurance;
    private Long nurseId;
}
