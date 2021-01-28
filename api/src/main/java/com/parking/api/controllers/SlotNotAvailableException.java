package com.parking.api.controllers;


public class SlotNotAvailableException extends RuntimeException {

    SlotNotAvailableException(long id) {
        super(String.format("The slot (%d) is not available!", id));
    }
}