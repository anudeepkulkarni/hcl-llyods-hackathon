package com.llyods.payments.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "payment_outcomes")
@Data
public class PaymentOutcomeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private UUID paymentId;
    private String debitAccountId;
    private String creditAccountId;
    private BigDecimal amount;
    private String currency;

    private String status; // PROCESSED | HELD | REJECTED

    private Instant processedAt;
    private long processingTimeMs;
}