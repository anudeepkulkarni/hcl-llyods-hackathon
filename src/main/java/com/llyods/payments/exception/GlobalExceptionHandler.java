package com.llyods.payments.exception;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ✅ 400 — Bean Validation (Scenario 3)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        List<Violation> violations = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new Violation(
                        err.getField(),
                        err.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ApiErrorResponse(
                Instant.now().toString(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                request.getRequestURI(),
                violations
        );
    }

    // ✅ 404 / 409 / 422 — Business errors
    @ExceptionHandler(ResponseStatusException.class)
    public ApiErrorResponse handleBusinessErrors(
            ResponseStatusException ex,
            HttpServletRequest request) {

        String error;

        if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
            error = "Account not found";
        } else if (ex.getStatusCode() == HttpStatus.CONFLICT) {
            error = "Duplicate paymentId";
        } else if (ex.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY) {
            error = "Account suspended";
        } else {
            error = ex.getReason();
        }

        return new ApiErrorResponse(
                Instant.now().toString(),
                ex.getStatusCode().value(),
                error,
                request.getRequestURI(),
                Collections.emptyList()   // ✅ MUST be empty
        );
    }
}