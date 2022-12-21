package com.medicalmanagement.services.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BillSumDTO {
    @NotNull
    private Float healthInsurance;
}
