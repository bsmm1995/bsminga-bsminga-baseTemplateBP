package com.pichincha.cell.template.domain;

import com.pichincha.cell.template.domain.base.AuditMetadata;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "ACCOUNT")
public class Account extends AuditMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "ACCOUNT_NUMBER", length = 16)
    private String accountNumber;

    @Column(name = "AMOUNT")
    private Double amount;

    @OneToOne(mappedBy = "account")
    private Card card;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    @ToString.Exclude
    private List<Transaction> transactionList;
}
