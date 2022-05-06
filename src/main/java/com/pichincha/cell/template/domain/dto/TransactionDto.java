package com.pichincha.cell.template.domain.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private Long id;
    private Long accountId;
    private String currency;
    private Double amount;
    private String description;
    private String transactionType;
}
