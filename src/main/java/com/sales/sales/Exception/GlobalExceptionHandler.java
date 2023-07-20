package com.sales.sales.Exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(StoreDetailsFoundException.class)
    public ResponseEntity<ErrorResponse> handleStoreDetailsNotFoundException(StoreDetailsFoundException e, WebRequest request)
    {
        ErrorResponse errorResponse=new ErrorResponse(LocalDateTime.now(),e.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e, WebRequest request)
    {
        ErrorResponse errorResponse=new ErrorResponse(LocalDateTime.now(),e.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception e, WebRequest request)
    {
        ErrorResponse errorResponse=new ErrorResponse(LocalDateTime.now(),e.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
