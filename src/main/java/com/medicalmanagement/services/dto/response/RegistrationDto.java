package com.medicalmanagement.services.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
    private Long id;
    private String unit;
    private Integer quantity;
    private Long patientId;
    private Long serviceId;
    private Long doctorId;
}
