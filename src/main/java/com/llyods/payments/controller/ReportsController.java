package com.llyods.payments.controller;

import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import com.llyods.payments.entity.PaymentOutcomeEntity;
import com.llyods.payments.repository.PaymentOutcomeRepository;

@RestController
@RequestMapping("/api/reports")
public class ReportsController {

    private final PaymentOutcomeRepository repo;

    public ReportsController(PaymentOutcomeRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/activity")
    public Page<PaymentOutcomeEntity> activity(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String status) {

        Pageable pageable = PageRequest.of(page, size,
                Sort.by("processedAt").descending());

        return status == null
                ? repo.findAll(pageable)
                : repo.findByStatus(status, pageable);
    }

    @GetMapping("/accounts/{accountId}/history")
    public List<PaymentOutcomeEntity> history(
            @PathVariable String accountId) {

        return repo.findByDebitAccountIdOrCreditAccountIdOrderByProcessedAtDesc(
                accountId, accountId);
    }
}