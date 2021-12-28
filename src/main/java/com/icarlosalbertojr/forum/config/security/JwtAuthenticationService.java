package com.icarlosalbertojr.forum.config.security;

import com.icarlosalbertojr.forum.models.User;
import com.icarlosalbertojr.forum.services.UserService;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class JwtAuthenticationService {

    @Value("${jwt.expiration}")
    private String TOKEN_EXPIRATION;
    @Value("${jwt.secret}")
    private String TOKEN_SECRET;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String getToken(Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        User user = userService.getUserByEmail(email);
        Date today = new Date();
        Date expiration = new Date(today.getTime() + Long.valueOf(TOKEN_EXPIRATION));
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET)
                .compact();
    }

    public boolean validateToken(String token) {
        return StringUtils.hasText(getSubjectToken(token));
    }

    public String getSubjectToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
