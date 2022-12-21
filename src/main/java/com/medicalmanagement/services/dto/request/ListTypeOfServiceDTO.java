package com.medicalmanagement.services.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
public class ListTypeOfServiceDTO {
    @NotEmpty
    private List<@NotBlank @Size(min = 1, max = 255) String> name;
}
