package com.proyectopulmonverde.userservice.Service;

import com.proyectopulmonverde.userservice.Entities.Token;
import com.proyectopulmonverde.userservice.Entities.User;
import com.proyectopulmonverde.userservice.Repository.RoleRepository;
import com.proyectopulmonverde.userservice.Repository.TokenRepository;
import com.proyectopulmonverde.userservice.Repository.UserRepository;
import com.proyectopulmonverde.userservice.Security.JwtService;
import com.proyectopulmonverde.userservice.Security.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public void register(RegistrationRequest request) {
        var userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Rol usuario no fue inicializado"));
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enable(true)
                .roles(List.of(userRole))
                .build();
        userRepository.save(user);
        sendValidationEmail(user);

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
            String token = jwtService.generateToken(user);
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




    private void sendValidationEmail(User user) {
        var newToken = generateAndSaveActivationToken (user);
    }

    private Object generateAndSaveActivationToken(User user) {
        String generationToken = generateActivationCode(6);
        var token = Token.builder()
                .token(generationToken)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);
        return generationToken;
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < length; i++){
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }
}
