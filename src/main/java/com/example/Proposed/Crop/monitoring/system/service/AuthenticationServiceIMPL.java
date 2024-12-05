package com.example.Proposed.Crop.monitoring.system.service;

import com.example.Proposed.Crop.monitoring.system.Dao.UserDao;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.PasswordDto;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.UserDto;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.UserEntity;
import com.example.Proposed.Crop.monitoring.system.exception.NotFoundException;
import com.example.Proposed.Crop.monitoring.system.secure.JWTAuthResponse;
import com.example.Proposed.Crop.monitoring.system.secure.Security.SignIn;
import com.example.Proposed.Crop.monitoring.system.secure.Security.SignUp;
import com.example.Proposed.Crop.monitoring.system.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class AuthenticationServiceIMPL implements AuthenticationService{
    private final Mapping mapping;
    private final UserDao userDao;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Override
    public JWTAuthResponse signUp(SignUp signUp) {
        UserDto userDTO =UserDto.builder().email(signUp.getEmail()).password(passwordEncoder.encode(signUp.getPassword())).role(String.valueOf(signUp.getRole())).build();
        UserEntity userEntity1 = mapping.toUserEntity(userDTO);
        System.out.println(userEntity1);
        userDao.save(userEntity1);
        System.out.println(userEntity1);
        String generateToken = jwtService.generateToken(userEntity1);
        return JWTAuthResponse.builder().token(generateToken).build();
    }

    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(),signIn.getPassword()));
        UserEntity userEntity=userDao.findByEmail(signIn.getEmail()).orElseThrow(()->new NotFoundException("User Not Found"));
        var generateToken = jwtService.generateToken(userEntity);
        return JWTAuthResponse.builder().token(generateToken).build();
    }

    @Override
    public JWTAuthResponse refreshToken(String refreshToken) {
        String user =jwtService.extractUserName(refreshToken);
        UserEntity findUser =userDao.findByEmail(user).orElseThrow(()-> new NotFoundException("Couldn't find User"));
        String token =jwtService.refreshToken(findUser);
        return JWTAuthResponse.builder().token(token).build();
    }

    @Override
    public void changePassword(PasswordDto passwordDto) {
        Optional<UserEntity> byEmail =userDao.findByEmail(passwordDto.getEmail());
        if (byEmail.isPresent()){
            UserEntity userEntity=userDao.getReferenceById(byEmail.get().getUsername());
            userEntity.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
            userDao.save(userEntity);
        }
    }
}
