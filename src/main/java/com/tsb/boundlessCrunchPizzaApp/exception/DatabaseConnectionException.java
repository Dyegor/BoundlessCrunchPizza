package com.tsb.boundlessCrunchPizzaApp.exception;

public class DatabaseConnectionException extends RuntimeException {

  public DatabaseConnectionException(String message) {
    super(message);
  }
}
