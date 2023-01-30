package com.medicalmanagement.services.dto.response;

import java.sql.Date;

public interface PatientProjection {
    Long getPatientID();
    String getFullName();
    String getGender();
    String getBirthday();
    String getAddress();
    String getPhone();
    Integer getInsurance();
    String getNurseName();
    Date getDateCreated();
    Long getNurseId();

}
