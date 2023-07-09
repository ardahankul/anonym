package com.example.anonym.authenticaion;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.anonym.config.JwtService;
import com.example.anonym.dto.RegisterRequest;
import com.example.anonym.entity.AppUser;
import com.example.anonym.entity.Role;
import com.example.anonym.repository.AppUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    public AuthenticationResponse register(RegisterRequest request) {

        Boolean isUnique = false;
        isUnique = isUsernameAndEmailUnique(request.getUsername(), request.getEmail()); 

        if (isUnique) {
            
            String guid = generateGuid(request.getUsername());

            var user = AppUser.builder()
                    .fullName(request.getFullName())
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .guid(guid)
                    .role(Role.USER)
                    .build();

            userRepository.save(user);

            var jwtToken = jwtService.generateToken(user);

            return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();

        } else {
            return null;
        }

        
    }

    private String generateGuid(String input) {
        UUID uuid = UUID.nameUUIDFromBytes(input.getBytes());
        return uuid.toString();
    }

    private Boolean isUsernameAndEmailUnique(String username, String email) {
        Boolean isUsernameUnique = !userRepository.existsByUsername(username);
        Boolean isEmailUniqe = !userRepository.existsByEmail(email);
        return (isUsernameUnique && isEmailUniqe);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );

        var user = userRepository.findByUsername(request.getUsername())
                                    .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
    
}
}
