package com.parking.api.controllers;

public class ReservationNotFoundException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    ReservationNotFoundException(String carId) {
        super(String.format("Could not find the reservation for the car (%s)!", carId));
      }
}