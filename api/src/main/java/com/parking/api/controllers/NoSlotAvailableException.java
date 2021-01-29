package com.parking.api.controllers;


public class NoSlotAvailableException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    NoSlotAvailableException(String type) {
        super(String.format("No slot is not available for the vehicle of type %s!", type));
    }
}