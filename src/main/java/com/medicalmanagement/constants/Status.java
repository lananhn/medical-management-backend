package com.medicalmanagement.constants;

public enum Status {
    ACTIVE(1, "Đang hoạt động"),
    INACTIVE(0, "Không còn hoạt động");

    private final Integer id;
    private final String name;

    Status(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
}
