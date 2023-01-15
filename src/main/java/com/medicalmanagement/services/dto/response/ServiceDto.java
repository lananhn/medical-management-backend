package com.medicalmanagement.services.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDto {
    private Long id;
    private String name;
    private Float price;
    private String unit;
    private Integer status;
    private Long typeOfService;
}
