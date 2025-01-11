package com.kb.exam.exceptions;

import lombok.Getter;

@Getter
public class CustomValidationException extends RuntimeException {
    public CustomValidationException(String message) {
        super(message);
    }


}

