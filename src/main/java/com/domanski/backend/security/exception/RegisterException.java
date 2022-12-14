package com.domanski.backend.security.exception;

import com.domanski.backend.common.exception.BusinessException;

public class RegisterException extends BusinessException {
    public RegisterException(String message) {
        super(message);
    }
}
