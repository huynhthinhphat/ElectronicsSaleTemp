package com.tip.b18.electronicsales.exceptions;

import com.tip.b18.electronicsales.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseDTO<?> errorResponse(String message) {
        return new ResponseDTO<>("error", message, null, null);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ResponseDTO<?>> handlingValidation(MethodArgumentNotValidException e){
        ResponseDTO<?> responseDTO = errorResponse(Objects.requireNonNull(e.getFieldError()).getDefaultMessage());
        return ResponseEntity.badRequest().body(responseDTO);
    }

    @ExceptionHandler(value = CredentialsException.class)
    ResponseEntity<ResponseDTO<?>> handlingLoginException(CredentialsException e){
        ResponseDTO<?> responseDTO = errorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDTO);
    }

    @ExceptionHandler(value = AlreadyExistsException.class)
    ResponseEntity<ResponseDTO<?>> handlingAlreadyExistsException(AlreadyExistsException e){
        ResponseDTO<?> responseDTO = errorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseDTO);
    }
}
