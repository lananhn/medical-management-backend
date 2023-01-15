package com.medicalmanagement.services.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
public class TypeOfServiceDTO {
    @NotBlank
    @Size(min = 1, max = 255)
    private String name;
}
