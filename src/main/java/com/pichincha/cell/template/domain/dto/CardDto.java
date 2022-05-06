package com.pichincha.cell.template.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CardDto {
    private Long id;
    private String number;
    private Date expirationDate;
    private AccountDto accountDto;
}
