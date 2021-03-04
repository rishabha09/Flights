package com.example.Flights.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ResponseBody
  @ExceptionHandler(ApplicationException.class)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public String applicationException(final ApplicationException applicationException) {

    return applicationException.getMsg();
  }

}
