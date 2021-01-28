package com.parking.api.controllers;

public class SlotNotFoundException extends RuntimeException {

    SlotNotFoundException(Long id) {
      super(String.format("Could not find the slot (%d)!", id));
    }
  }