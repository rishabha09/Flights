package com.example.Flights.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    private String msg;

    public ApplicationException(String msg){
      this.msg = msg;
    }

}
