package com.pichincha.cell.template.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CustomerDto {
    private Long id;

    @NotBlank(message = "The DNI cannot be null and void.")
    @Size(min = 10, max = 13, message = "The DNI must be a string of between 10 and 13 numeric digits.")
    private String dni;

    @NotBlank(message = "The name cannot be null and void.")
    private String name;

    private String lastname;

    private List<AccountDto> accountDtoList;
}
