package com.medicalmanagement.services.dto;

import com.medicalmanagement.constants.ERole;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Data
public class RoleDTO {
    @NotBlank
    @Enumerated(EnumType.STRING)
    private ERole name;
}
