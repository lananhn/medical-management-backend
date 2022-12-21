package com.medicalmanagement.services.dto.response;

import java.sql.Date;

public interface BillProjection {
    Long getBillExId();
    Float getMoneyReceived();
    Date getDateCreated();
    String getPatientName();
    String getServiceName();
    String getAccountantName();
    Float getInsurancePayment();
    Float getSurplus();
    String getUnit();
    Integer getQuantity();
    Integer getStatus();
}
