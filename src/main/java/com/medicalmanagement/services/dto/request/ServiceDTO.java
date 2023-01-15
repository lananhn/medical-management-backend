package com.medicalmanagement.services.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
public class ServiceDTO {
    @NotBlank
    private String name;
    @NotNull
    private Float price;
    @NotBlank
    @Size(min = 1, max = 20)
    private String unit;

    private Long typeOfService;
}
