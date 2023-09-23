package com.esp.manilla.controller;


import com.esp.manilla.model.dto.AuthResponseDto;
import com.esp.manilla.model.dto.LoginRequestDto;
import com.esp.manilla.model.dto.RegisterRequestDto;
import com.esp.manilla.service.AuthService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173/")
public class AuthController {

  private final AuthService authService;

  @PostMapping
  public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto registerRequestDto) {
    return ResponseEntity.ok(authService.login(registerRequestDto));
  }
  
  @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto registerRequestDto) {
    return ResponseEntity.ok(authService.register(registerRequestDto));
  }
}
