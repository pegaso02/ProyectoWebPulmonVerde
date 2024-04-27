package com.proyectopulmonverde.userservice.Service;


import com.proyectopulmonverde.userservice.Security.RegistrationRequest;
import com.proyectopulmonverde.userservice.Entities.Token;
import com.proyectopulmonverde.userservice.Entities.User;
import com.proyectopulmonverde.userservice.Repository.RoleRepository;
import com.proyectopulmonverde.userservice.Repository.TokenRepository;
import com.proyectopulmonverde.userservice.Repository.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
//    private final EmailService emailService;

//    @Value("http://localhost:4200/activate-acount")
//    private String activationUrl;

    public void register(RegistrationRequest request) throws MessagingException {
        var userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Rol usuario no fue inicializado"));
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enable(false)
                .roles(List.of(userRole))
                .build();
        userRepository.save(user);
        //sendValidationEmail(user);


    }

    /*
    private void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveActivationToken (user);

        emailService.sendEmail(
                user.getEmail(),
                user.fullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Cuenta activada"
        );
    }

*/


    private String generateAndSaveActivationToken(User user) {
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
