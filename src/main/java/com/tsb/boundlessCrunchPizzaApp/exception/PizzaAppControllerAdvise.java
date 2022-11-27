package com.tsb.boundlessCrunchPizzaApp.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.REQUEST_TIMEOUT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.ResponseEntity.status;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PizzaAppControllerAdvise {

  @ExceptionHandler(value = {InvalidRequestException.class})
  public ResponseEntity<String> handleInvalidRequest(InvalidRequestException ex) {

    return status(BAD_REQUEST)
        .contentType(APPLICATION_JSON)
        .body(ex.getMessage());
  }

  @ExceptionHandler(value = {DatabaseConnectionException.class})
  public ResponseEntity<String> handleDatabaseConnectionException(DatabaseConnectionException ex) {

    return status(REQUEST_TIMEOUT)
        .contentType(APPLICATION_JSON)
        .body(ex.getMessage());
  }
}
