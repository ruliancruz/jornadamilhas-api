package com.jornadamilhas.api.model.infrastructure;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandling
{

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404()
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No destination found");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException exception)
    {
        List<FieldError> errorList = exception.getFieldErrors();

        return ResponseEntity.badRequest().body(errorList.stream().map(ErrorValidationData::new));
    }
}