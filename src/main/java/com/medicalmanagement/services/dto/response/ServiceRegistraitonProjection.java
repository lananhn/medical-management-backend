package com.medicalmanagement.services.dto.response;

import java.sql.Date;

public interface ServiceRegistraitonProjection {
    Long getRegId();
    String getPatientName();
    String getServiceName();
    String getDoctorName();
    String getUnit();
    Integer getQuantity();
    Date getDateCreated();
    Long getDoctorId();
}
