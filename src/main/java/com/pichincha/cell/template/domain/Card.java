package com.pichincha.cell.template.domain;

import com.pichincha.cell.template.domain.base.Audit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "CARD")
public class Card extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CAR_NUMBER", length = 16)
    private String number;

    @Column(name = "EXPIRATION_DATE")
    private Date expirationDate;

    @OneToOne
    @JoinColumn(name = "ID")
    private Account account;
}
