package com.icarlosalbertojr.forum.exceptions.dto;

public class ValidationExceptionDto {

    private String field;
    private String message;

    public ValidationExceptionDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
