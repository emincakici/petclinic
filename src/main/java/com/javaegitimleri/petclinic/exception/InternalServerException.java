package com.javaegitimleri.petclinic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by EMINCAKICI on Feb Mon 10,2020
 */
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {
    public InternalServerException(Throwable cause) {
        super(cause);
    }
}
