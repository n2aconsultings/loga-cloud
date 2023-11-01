package com.loga.reportingservice.monitoring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReportingFailedException extends RuntimeException{
    public ReportingFailedException(String message) {
        super(message);
    }
}
