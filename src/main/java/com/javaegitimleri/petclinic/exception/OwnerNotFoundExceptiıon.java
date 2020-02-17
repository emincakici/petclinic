package com.javaegitimleri.petclinic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by EMINCAKICI on Feb, 2020
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class OwnerNotFoundExceptiıon extends RuntimeException {
    public OwnerNotFoundExceptiıon(String message) {
        super(message);
    }
}
