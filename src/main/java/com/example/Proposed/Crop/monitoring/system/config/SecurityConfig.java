package com.example.Proposed.Crop.monitoring.system.config;

import com.example.Proposed.Crop.monitoring.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserService userService;
    private final JWTConfigFilter jwtConfigFilter;
}
