package com.example.Proposed.Crop.monitoring.system.config;

import com.example.Proposed.Crop.monitoring.system.service.AuthService;
import com.example.Proposed.Crop.monitoring.system.service.JwtService;
import com.example.Proposed.Crop.monitoring.system.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Configuration
@RequiredArgsConstructor
public class JWTConfigFilter extends OncePerRequestFilter {
   private final JwtService jwtService;
   private final UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws ServletException, IOException {


    }
}
