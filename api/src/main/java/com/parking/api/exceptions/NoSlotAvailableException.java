package com.parking.api.exceptions;


public class NoSlotAvailableException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NoSlotAvailableException(String type) {
        super(String.format("No slot is not available for the vehicle of type %s!", type));
    }
}