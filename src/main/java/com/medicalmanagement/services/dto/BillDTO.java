package com.medicalmanagement.services.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BillDTO {
    @NotNull
    private Float moneyReceived;
    private Long serviceReg;
    private Long accountantId;
}
