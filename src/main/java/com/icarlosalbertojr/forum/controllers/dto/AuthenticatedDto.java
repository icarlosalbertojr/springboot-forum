package com.icarlosalbertojr.forum.controllers.dto;

import lombok.Data;

@Data
public class AuthenticatedDto {
    private String token;
    private String authType;

    public AuthenticatedDto(String token, String authType) {
        this.token = token;
        this.authType = authType;
    }
}
