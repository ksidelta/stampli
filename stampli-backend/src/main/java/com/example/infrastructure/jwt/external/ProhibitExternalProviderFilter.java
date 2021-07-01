package com.example.infrastructure.jwt.external;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    static private Logger logger = LoggerFactory.getLogger(ProhibitExternalProviderFilter.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        // Only standard JwtAuthenticationProvider which are given for external OAuth servers give JwtAuthenticationToken
        if (authentication instanceof JwtAuthenticationToken) {
            if (!request.getServletPath().equals(TOKEN_AUTHENTICATOR_PATH)) {
                logger.warn("Path {} not matching permitted token login path: {}", request.getServletPath(), TOKEN_AUTHENTICATOR_PATH);
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        }

        filterChain.doFilter(request, response);
    }
}
