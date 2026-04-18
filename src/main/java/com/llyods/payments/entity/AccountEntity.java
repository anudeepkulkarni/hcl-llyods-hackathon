package com.llyods.payments.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
public class AccountEntity {

    @Id
    private String accountId;

    private String accountName;
    private String accountType;
    private String status;
    private String currency;
    private LocalDate openedDate;
}