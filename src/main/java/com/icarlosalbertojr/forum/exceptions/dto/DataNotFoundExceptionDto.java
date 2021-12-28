package com.icarlosalbertojr.forum.exceptions.dto;

public class DataNotFoundExceptionDto {

    private String message;

    public DataNotFoundExceptionDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
