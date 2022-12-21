package com.medicalmanagement.services.dto.response;

import java.util.Date;

public interface ResultsExamProjection {
    Long getResultsId();
    Date getDateCreated();
    String getResult();
    String getDescription();
    String getServiceName();
    String getPatientName();
    String getDoctorName();
}
