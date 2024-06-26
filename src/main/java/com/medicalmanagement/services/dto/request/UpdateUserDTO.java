package com.medicalmanagement.services.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@RequiredArgsConstructor
public class UpdateUserDTO {
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
    @Size(max = 12)
    private String phone;
    @NotNull
    private Integer status;
}
