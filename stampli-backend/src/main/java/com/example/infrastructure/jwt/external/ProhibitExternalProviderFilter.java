package com.example.infrastructure.jwt.external;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProhibitExternalProviderFilter extends OncePerRequestFilter {
    static private String TOKEN_AUTHENTICATOR_PATH = "/api/authentication/login/token";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        // Only standard JwtAuthenticationProvider which are given for external OAuth servers give JwtAuthenticationToken
        if (authentication instanceof JwtAuthenticationToken) {
            if (!request.getContextPath().equals(TOKEN_AUTHENTICATOR_PATH)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                response.getOutputStream().write("External tokens may not be used outside of token login".getBytes());
            }
        }

        filterChain.doFilter(request, response);
    }
}
