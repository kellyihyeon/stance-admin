package com.github.kellyihyeon.stanceadmin.shared.exception;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class ErrorResponse {

    private final ErrorCode errorCode;
    private final List<String> errorFields;
    private final String requestUri;
    private final String timestamp;

    public ErrorResponse(ErrorCode errorCode, List<String> errorFields, String requestUri) {
        this.errorCode = errorCode;
        this.errorFields = errorFields;
        this.requestUri = requestUri;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
