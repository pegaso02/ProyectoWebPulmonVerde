package com.proyectopulmonverde.userservice.Service;

import com.proyectopulmonverde.userservice.Entities.Role;
import com.proyectopulmonverde.userservice.Entities.Token;
import com.proyectopulmonverde.userservice.Entities.TokenType;
import com.proyectopulmonverde.userservice.Entities.User;
import com.proyectopulmonverde.userservice.Repository.TokenRepository;
import com.proyectopulmonverde.userservice.Repository.UserRepository;
import com.proyectopulmonverde.userservice.Security.JwtService;
import com.proyectopulmonverde.userservice.Security.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegistrationRequest request) {
        Role role = request.getRole() != null ? request.getRole() : Role.USER;
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enable(true)
                .role(role)
                .build();
        var savedUser = userRepository.save(user);
        var token = jwtService.generateToken(user);
        saveUserToken(savedUser, token);

        return AuthenticationResponse.builder()
                .accessToken(token)
                .build();

    }

    public String authenticate(String email, String password) {
        try {
            System.out.println("Autenticando usuario: " + email);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("Usuario autenticado con éxito: " + email);

            User user = (User) authentication.getPrincipal();
            var token = jwtService.generateToken(user);
            System.out.println("Token generado: " + token);
            return token;
        } catch (AuthenticationException e) {
            System.err.println("Error en la autenticación para el usuario: " + email);
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            System.err.println("Error inesperado durante la autenticación o generación de token para el usuario: " + email);
            e.printStackTrace();
            throw new RuntimeException("Error interno del servidor", e);
        }
    }


    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(60))
                .build();
        tokenRepository.save(token);
    }

}
