package com.shivag.roomly.security;



import com.shivag.roomly.service.CustomUserDetailsService;
import com.shivag.roomly.utils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization"); //getting token with bearer
        final String jwtToken;
        final String userEmail;

        if (authHeader == null || authHeader.isBlank()) {
            filterChain.doFilter(request, response); //filter this blank request or request without token
            return;
        }

        jwtToken = authHeader.substring(7); // removing bearer from token fetched
        userEmail = jwtUtils.extractUsername(jwtToken);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) { //checking if the user is not already authenticated
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(userEmail); // get his username
            if (jwtUtils.isValidToken(jwtToken, userDetails)) { //validate his token
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext(); //If the token is valid, an empty SecurityContext is created.
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); //Then, a UsernamePasswordAuthenticationToken object is created using the user's details. This object represents a fully authenticated user with roles/authorities.
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));//The token is associated with the request's details (setDetails()), so that additional information like the user's IP address can be tracked.
                securityContext.setAuthentication(token);//The securityContext.setAuthentication(token) sets this user as authenticated.
                SecurityContextHolder.setContext(securityContext);//Finally, SecurityContextHolder.setContext(securityContext) places this authentication information into the global security context, so other parts of the application can know the user is authenticated.
            }
        }
        filterChain.doFilter(request, response);//moving further filtering
    }
}
