package com.javaegitimleri.petclinic.exception;

/**
 * Created by EMINCAKICI on Feb Fri 21,2020
 */
public class VetNotFoundException extends RuntimeException {
    public VetNotFoundException(String message) {
        super(message);
    }
}
