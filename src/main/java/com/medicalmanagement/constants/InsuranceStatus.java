package com.medicalmanagement.constants;

public enum InsuranceStatus {
    HAVEINSURANCE(1, "Có bảo hiểm y tế"),
    NOINSURANCE(0, "Không có bảo hiểm y tế");

    private final Integer id;
    private final String name;

    InsuranceStatus(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
}
