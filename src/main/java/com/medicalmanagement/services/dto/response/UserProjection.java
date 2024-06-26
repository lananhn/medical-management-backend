package com.medicalmanagement.services.dto.response;

import java.util.List;

public interface UserProjection {
    Long getUserId();
    String getUsername();
    String getPassword();
    String getName();
    String getAddress();
    String getEmail();
    String getPhone();
    Integer getStatus();
    Integer getRoleId();
    String getRoleName();
}
