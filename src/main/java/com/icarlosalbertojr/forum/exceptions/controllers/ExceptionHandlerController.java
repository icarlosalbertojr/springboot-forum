package com.icarlosalbertojr.forum.exceptions.controllers;

import com.icarlosalbertojr.forum.exceptions.DataNotFoundException;
import com.icarlosalbertojr.forum.exceptions.dto.DataNotFoundExceptionDto;
import com.icarlosalbertojr.forum.exceptions.dto.ValidationExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ValidationExceptionDto> validationExceptionHandler(MethodArgumentNotValidException exception) {
        List<ValidationExceptionDto> responseErrors = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            responseErrors.add(new ValidationExceptionDto(e.getField(), e.getDefaultMessage()));
        });
        return responseErrors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataNotFoundException.class)
    public DataNotFoundExceptionDto dataNotFoundHandler(DataNotFoundException exception) {
        return new DataNotFoundExceptionDto(exception.getMessage());
    }


}
