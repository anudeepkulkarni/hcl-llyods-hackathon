package com.hcl.hackaton.exception;

public class ResourceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = -19432060095894486L;

  /**
   * Constructs a new ResourceNotFoundException with the specified detail message.
   *
   * @param message the detail message
   */
  public ResourceNotFoundException(final String message) {
    super(message);
  }

}
