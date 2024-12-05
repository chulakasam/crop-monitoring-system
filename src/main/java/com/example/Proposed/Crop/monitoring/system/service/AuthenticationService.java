package com.example.Proposed.Crop.monitoring.system.service;

import com.example.Proposed.Crop.monitoring.system.Dto.Impl.PasswordDto;
import com.example.Proposed.Crop.monitoring.system.secure.JWTAuthResponse;
import com.example.Proposed.Crop.monitoring.system.secure.Security.SignIn;
import com.example.Proposed.Crop.monitoring.system.secure.Security.SignUp;

public interface AuthenticationService {
    JWTAuthResponse signUp(SignUp signUp);
    JWTAuthResponse signIn(SignIn signIn);
    JWTAuthResponse refreshToken(String refreshToken);
    void changePassword(PasswordDto passwordDto);
}
