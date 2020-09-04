package com.carLease.carLease.expections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
class AppExceptionsHandler {

    @ExceptionHandler(RequestProblemException.class)
    public ResponseEntity<Object> handleApiRequestException(RequestProblemException e){
        ErrorResponse errorResponse= new ErrorResponse(e.getMessage(),HttpStatus.BAD_REQUEST, ZonedDateTime.now((ZoneId.of("Z"))));

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }


}
