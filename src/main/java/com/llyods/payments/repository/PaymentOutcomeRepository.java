package com.llyods.payments.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.llyods.payments.entity.PaymentOutcomeEntity;

public interface PaymentOutcomeRepository
        extends JpaRepository<PaymentOutcomeEntity, Long> {

    Page<PaymentOutcomeEntity> findByStatus(String status, Pageable pageable);

    List<PaymentOutcomeEntity> findByDebitAccountIdOrCreditAccountIdOrderByProcessedAtDesc(
            String debit, String credit);
}