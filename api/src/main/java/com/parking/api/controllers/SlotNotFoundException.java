package com.parking.api.controllers;

public class SlotNotFoundException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  SlotNotFoundException(Long id) {
      super(String.format("Could not find the slot (%d)!", id));
    }
}