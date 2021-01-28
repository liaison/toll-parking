package com.parking.api.controllers;

public class SlotNotFoundException extends RuntimeException {

    SlotNotFoundException(Long id) {
      super("Could not find the slot: " + id);
    }
  }