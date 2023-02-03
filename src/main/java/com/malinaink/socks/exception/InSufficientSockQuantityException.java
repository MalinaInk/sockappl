package com.malinaink.socks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InSufficientSockQuantityException extends RuntimeException {
    public InSufficientSockQuantityException(String message) {
        super(message);
    }
}
