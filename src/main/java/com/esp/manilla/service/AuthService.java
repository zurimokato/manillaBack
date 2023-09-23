package com.esp.manilla.service;

import com.esp.manilla.model.*;
import com.esp.manilla.model.dto.AuthResponseDto;
import com.esp.manilla.model.dto.LoginRequestDto;
import com.esp.manilla.model.dto.RegisterRequestDto;
import com.esp.manilla.model.enums.Role;
import com.esp.manilla.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDto login(LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwt = jwtService.generateToken(user);
        return AuthResponseDto.builder()
                .token(jwt).token(jwt).id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public AuthResponseDto register(RegisterRequestDto request) {
        var user = Usuario.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        var u = userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return AuthResponseDto.builder()
                .token(jwt).id(u.getId())
                .name(u.getName())
                .email(u.getEmail())
                .build();
    }
}
