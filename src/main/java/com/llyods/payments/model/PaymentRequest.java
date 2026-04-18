package com.llyods.payments.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PaymentRequest(
        UUID paymentId,
        String debitAccountId,
        String creditAccountId,
        BigDecimal amount,
        String currency,
        Instant timestamp
) {}
