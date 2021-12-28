package com.icarlosalbertojr.forum.config.security;

import com.icarlosalbertojr.forum.models.User;
import com.icarlosalbertojr.forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private JwtAuthenticationService jwtAuthenticationService;
    private UserService userService;

    public JwtAuthorizationFilter(JwtAuthenticationService jwtAuthenticationService, UserService userService) {
        this.jwtAuthenticationService = jwtAuthenticationService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = extractTokenFromHeader(request);
        if (StringUtils.hasText(token)) {
            authenticate(token);
        }
        filterChain.doFilter(request, response);
    }

    private void authenticate(String token) {
        String tokenSubject = jwtAuthenticationService.getSubjectToken(token);
        User user = userService.getById(Long.valueOf(tokenSubject));
        SecurityContextHolder
                .getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), user.getAuthorities()));
    }


    private String extractTokenFromHeader(HttpServletRequest request) {
        String authContent = request.getHeader("Authorization");
        if (!StringUtils.hasText(authContent)) {
            return "";
        }
        String token = authContent.replace("Bearer", "").trim();
        System.out.println(jwtAuthenticationService.validateToken(token));
        return token;
    }
}
