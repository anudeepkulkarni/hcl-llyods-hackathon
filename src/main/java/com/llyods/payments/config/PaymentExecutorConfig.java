package com.llyods.payments.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentExecutorConfig {

	@Bean
	public Executor paymentExecutor() {
		return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	}
}
