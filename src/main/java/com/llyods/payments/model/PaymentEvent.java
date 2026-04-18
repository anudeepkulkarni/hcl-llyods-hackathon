package com.llyods.payments.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PaymentEvent {

	@NotNull(message = "Payment ID must be a valid UUID")
	private UUID paymentId;

	@NotBlank(message = "Debit account ID must not be blank")
	private String debitAccountId;

	@NotBlank(message = "Credit account ID must not be blank")
	private String creditAccountId;

	@NotNull
	@DecimalMin(value = "0.01", message = "Amount must be greater than 0")
	private BigDecimal amount;

	@NotBlank
	@Size(min = 3, max = 3)
	private String currency;

	@Size(max = 35)
	private String reference;

	@NotNull
	@PastOrPresent
	private Instant timestamp;
}