package com.github.kellyihyeon.stanceadmin.shared.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // 400 BAD_REQUEST
    MISSING_REQUIRED_FIELD(HttpStatus.BAD_REQUEST),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST),

    // 404 NOT_FOUND
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND),

    // 409 CONFLICT
    DUPLICATE_MEMBER(HttpStatus.CONFLICT);

    private final HttpStatus httpStatus;

    ErrorCode(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
