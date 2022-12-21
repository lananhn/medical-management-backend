package com.medicalmanagement.services.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Setter
@Getter
public class UserDTO {
    @NotBlank
    @Size(max = 50)
    private String userName;
    @NotBlank
    @Size(max = 20)
    private String passWord;
    @NotBlank
    @Size(max = 255)
    private String name;
    @Size(max = 255)
    private String address;
    @NotBlank
    @Size(max = 255)
    private String email;
    @NotBlank
    @Size(max = 255)
    private String position;
    @Size(max = 12)
    private String phone;
    private Integer status;
    private Set<String> roles;
}
