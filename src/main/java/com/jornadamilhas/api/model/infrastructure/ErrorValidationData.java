package com.jornadamilhas.api.model.infrastructure;

import org.springframework.validation.FieldError;

public record ErrorValidationData(String fieldName, String message)
{
    public ErrorValidationData(FieldError error)
    {
        this(error.getField(), error.getDefaultMessage());
    }
}