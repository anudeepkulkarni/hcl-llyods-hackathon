package com.llyods.payments.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.llyods.payments.service.impl.PaymentProcessingListener;

@RestController
@RequestMapping("/api/metrics")
public class MetricsController {

	private final PaymentProcessingListener listener;

	public MetricsController(PaymentProcessingListener listener) {
		this.listener = listener;
	}

	@GetMapping("/summary")
	public Map<String, Object> summary() {
		return Map.of("totalProcessed", listener.getTotalProcessed(), "totalHeld", listener.getTotalHeld(),
				"totalRejected", listener.getTotalRejected(), "avgProcessingTimeMs", listener.getAvgProcessingTime());
	}
}