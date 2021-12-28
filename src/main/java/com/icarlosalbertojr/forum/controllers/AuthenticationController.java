package com.icarlosalbertojr.forum.controllers;

import com.icarlosalbertojr.forum.config.security.JwtAuthenticationService;
import com.icarlosalbertojr.forum.controllers.dto.AuthenticatedDto;
import com.icarlosalbertojr.forum.controllers.dto.LoginDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtAuthenticationService jwtAuthenticationService;
    private Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping
    public ResponseEntity<?> authentication(@RequestBody @Valid LoginDto loginDto) {
        Authentication authentication = loginDto.toAuth();
        try {
            authenticationManager.authenticate(authentication);
            String token = jwtAuthenticationService.getToken(authentication);
            log.info("User authenticated successful!");
            return ResponseEntity.ok(new AuthenticatedDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            log.error("User can't be authenticated!");
            return ResponseEntity.badRequest().build();
        }
    }
}
