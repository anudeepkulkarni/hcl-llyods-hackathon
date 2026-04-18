package com.llyods.payments.service;

import com.llyods.payments.model.PaymentRequest;

public interface PaymentProcessingService {

	void process(PaymentRequest request);
}
