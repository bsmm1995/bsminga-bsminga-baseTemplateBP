package com.pichincha.cell.template.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class AccountDto {
    private Long id;
    private Long customerId;
    private String accountNumber;
    private Double amount;
    private CardDto cardDto;
    private List<TransactionDto> transactionDtoList;
}
