package com.example.anonym.authenticaion;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.anonym.dto.RegisterRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
         @RequestBody RegisterRequest request) {
            AuthenticationResponse response = authenticationService.register(request); 
            return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
         @RequestBody AuthenticationRequest request) {
            AuthenticationResponse response = authenticationService.authenticate(request);
             return ResponseEntity.ok(response);
    }
}
    