package ru.sergalas.perpay.security.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserSignException extends Throwable {

    public UserSignException(String message) {
        super(message);
    }
}
