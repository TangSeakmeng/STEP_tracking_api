package com.seakmeng.tracking_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends Exception {

    public AuthenticationException(String message) {
        super(message);
    }
    
}
