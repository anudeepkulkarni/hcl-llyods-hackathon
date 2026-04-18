package com.llyods.payments.service.impl;

import java.time.Instant;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.llyods.payments.entity.PaymentOutcomeEntity;
import com.llyods.payments.model.PaymentEvent;
import com.llyods.payments.repository.PaymentOutcomeRepository;

@Service
public class PaymentProcessingListener {

    private final PaymentOutcomeRepository repo;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    // live counters
    private long totalProcessed = 0;
    private long totalHeld = 0;
    private long totalRejected = 0;
    private long totalTime = 0;

    public PaymentProcessingListener(PaymentOutcomeRepository repo,
                                     KafkaTemplate<String, Object> kafkaTemplate) {
        this.repo = repo;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "payments.submitted")
    public void consume(PaymentEvent event) {

        long start = System.currentTimeMillis();
        String status;

        if (event.getAmount().compareTo(new java.math.BigDecimal("250000")) > 0) {
            status = "HELD";
            totalHeld++;
        } else if (event.getDebitAccountId().equals(event.getCreditAccountId())) {
            status = "REJECTED";
            totalRejected++;
        } else {
            status = "PROCESSED";
            totalProcessed++;
        }

        long duration = System.currentTimeMillis() - start;
        totalTime += duration;

        PaymentOutcomeEntity entity = new PaymentOutcomeEntity();
        entity.setPaymentId(event.getPaymentId());
        entity.setDebitAccountId(event.getDebitAccountId());
        entity.setCreditAccountId(event.getCreditAccountId());
        entity.setAmount(event.getAmount());
        entity.setCurrency(event.getCurrency());
        entity.setStatus(status);
        entity.setProcessedAt(Instant.now());
        entity.setProcessingTimeMs(duration);

        repo.save(entity);

        kafkaTemplate.send("payments.processed", event.getDebitAccountId(), entity);
    }

    // getters for metrics
    public long getTotalProcessed() { return totalProcessed; }
    public long getTotalHeld() { return totalHeld; }
    public long getTotalRejected() { return totalRejected; }
    public long getAvgProcessingTime() {
        return (totalProcessed + totalHeld + totalRejected) == 0
                ? 0
                : totalTime / (totalProcessed + totalHeld + totalRejected);
    }
}