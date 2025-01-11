package com.kb.exam.exception;

import com.kb.exam.common.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    // 사용자 정의한 특정 예외에 대한 처리
    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<CommonResponse> handleCustomValidationException(CustomValidationException ex) {
        return ResponseEntity.ok(new CommonResponse(ex.getMessage()));
    }
}
