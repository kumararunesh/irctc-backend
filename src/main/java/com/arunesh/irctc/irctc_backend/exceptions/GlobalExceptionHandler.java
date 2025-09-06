package com.arunesh.irctc.irctc_backend.exceptions;

import com.arunesh.irctc.irctc_backend.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchExcpetion(NoSuchElementException exception)
    {
        System.out.println("Error Found");
        ErrorResponse errorResponse =new ErrorResponse(exception.getMessage(),"404",false);
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception)
    {
        ErrorResponse errorResponse =new ErrorResponse(exception.getMessage(),"404",false);
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);

    }

    //hanlde method not valid exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException
            (MethodArgumentNotValidException exception){

        Map<String,String> errorResponse = new HashMap<>();
//        exception.getBindingResult().getAllErrors().forEach(error->
//        {
//            String errorMessage =error.getDefaultMessage();
//            String field =((FieldError)error).getField();
//            errorResponse.put(field,errorMessage);
//        });

        exception.getBindingResult().getFieldErrors().forEach(error ->
                errorResponse.put(error.getField(), error.getDefaultMessage()));
        ResponseEntity<Map<String,String>> error= new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
        return error;
    }
}

