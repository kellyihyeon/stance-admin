package com.github.kellyihyeon.stanceadmin.shared.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        List<String> invalidFields = new LinkedList<>();
        List<String> missingFields = new LinkedList<>();

        for (FieldError fieldError : fieldErrors) {
            if (isRequiredFieldMissing(fieldError)) {
                missingFields.add(fieldError.getField());
            } else {
                invalidFields.add(fieldError.getField());
            }
        }

        return createErrorResponse(missingFields, invalidFields, request.getRequestURI());
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(List<String> missingFields, List<String> invalidFields, String requestUri) {
        if (!missingFields.isEmpty()) {
            return ResponseEntity
                    .status(ErrorCode.MISSING_REQUIRED_FIELD.getHttpStatus())
                    .body(new ErrorResponse(ErrorCode.MISSING_REQUIRED_FIELD, missingFields, requestUri));
        }

        return ResponseEntity
                .status(ErrorCode.INVALID_INPUT_VALUE.getHttpStatus())
                .body(new ErrorResponse(ErrorCode.INVALID_INPUT_VALUE, invalidFields, requestUri));
    }

    private boolean isRequiredFieldMissing(FieldError fieldError) {
        final String NOT_BLANK = "NotBlank";
        final String NOT_NULL = "NotNull";

        return NOT_BLANK.equals(fieldError.getCode()) || NOT_NULL.equals(fieldError.getCode());
    }
}
