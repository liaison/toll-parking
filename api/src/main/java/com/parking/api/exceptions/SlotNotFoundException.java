package com.parking.api.exceptions;

public class SlotNotFoundException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public SlotNotFoundException(Long id) {
      super(String.format("Could not find the slot (%d)!", id));
    }
}