package com.cision.assignment.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage methodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request) {
        return new ErrorMessage(
               exception.getAllErrors()
                       .stream()
                       .map(ObjectError::getDefaultMessage)
                       .collect(Collectors.toList()));
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage messageNotReadableException(HttpMessageNotReadableException exception, WebRequest request) {
        return new ErrorMessage(List.of(
                Optional.ofNullable(exception.getMessage())
                        .orElse("")));
    }
}
