package com.joelio.taskManagement.Controller.utils;

import com.joelio.taskManagement.exception.ApiErros;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleResourceNotFoundException(MethodArgumentNotValidException exception){
        ApiErros apiErros = ApiErros.newBuilder()
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Erro de validação")
                .timestamp(new Date().getTime())
                .errors(exception.getBindingResult().getAllErrors()
                        .stream()
                        .map(objectError -> objectError.getDefaultMessage())
                        .collect(Collectors.toList()));
        return new ResponseEntity<>(apiErros,HttpStatus.BAD_REQUEST);
    }
}
