package com.shivag.roomly.security;

import com.shivag.roomly.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JWTAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable) //disabling csrf because we are using jwt
                .cors(Customizer.withDefaults()) //This allows requests from different origins.
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/auth/**", "/rooms/**", "/bookings/**").permitAll()
                        .anyRequest().authenticated()) //Specific routes (/auth/**, /rooms/**, /bookings/**) are marked as public, meaning anyone can access them without authentication.
                //All other requests (anyRequest()) require authentication.
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //This tells Spring Security not to create an HTTP session. The app is stateless, meaning it does not store any user session data in memory. Each request must include a valid JWT token to authenticate the user.
                .authenticationProvider(authenticationProvider()) //authenticate the user
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                      ; //Adds the custom jwtAuthFilter to validate JWT tokens before the default UsernamePasswordAuthenticationFilter

        return httpSecurity.build(); //this builds and returns the configured SecurityFilterChain to the Spring Security framework.
    }

    @Bean
    public AuthenticationProvider authenticationProvider() { //child of auth manager
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
        //DaoAuthenticationProvider authenticates users against a database using the UserDetailsService (which loads user data) and a PasswordEncoder.
        //It sets the CustomUserDetailsService to fetch user data and the passwordEncoder() (BCrypt) to encode and verify passwords.
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //encode password using bcrypt
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception { //main entry point of security authentication flow and parent of auth provider
        return authenticationConfiguration.getAuthenticationManager();
    }


}


