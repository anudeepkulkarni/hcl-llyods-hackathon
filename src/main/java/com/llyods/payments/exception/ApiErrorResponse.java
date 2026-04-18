package com.llyods.payments.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorResponse {

    private String timestamp;
    private int status;
    private String error;
    private String path;
    private List<Violation> violations;
}