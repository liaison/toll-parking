package com.parking.api.controllers;


public class NoSlotAvailableException extends RuntimeException {

    NoSlotAvailableException(String type) {
        super(String.format("No slot is not available for the vehicle of type %s!", type));
    }
}