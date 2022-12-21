package com.medicalmanagement.services.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@RequiredArgsConstructor
public class AddRegDTO {
    @NotBlank
    @Size(min = 1, max = 20)
    private String unit;
    @NotNull
    private Integer quantity;
    @NotNull
    private Long patientId;
    @NotEmpty
    private List<Long> serviceId;
    @NotNull
    private Long doctorId;
}
