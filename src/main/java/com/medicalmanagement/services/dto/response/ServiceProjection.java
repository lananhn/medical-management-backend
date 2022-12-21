package com.medicalmanagement.services.dto.response;

public interface ServiceProjection {
    Long getId();
    String getName();
    Float getPrice();
    String getUnit();
    Integer getStatus();
    Long getTypeOfServiceId();

}
