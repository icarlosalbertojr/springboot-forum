package com.icarlosalbertojr.forum.controllers.dto;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class LoginDto {

    @Email
    private String email;
    @NotNull
    private String password;

    public UsernamePasswordAuthenticationToken toAuth() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
