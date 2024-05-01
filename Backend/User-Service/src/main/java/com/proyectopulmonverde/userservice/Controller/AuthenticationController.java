package com.proyectopulmonverde.userservice.Controller;

import com.proyectopulmonverde.userservice.Entities.User;
import com.proyectopulmonverde.userservice.Repository.UserRepository;
import com.proyectopulmonverde.userservice.Security.RegistrationRequest;
import com.proyectopulmonverde.userservice.Service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService service;
    private final UserRepository userRepository;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(
            @RequestBody @Valid RegistrationRequest request
    ) {
        service.register(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with email: " + email);
        }
    }

    @PutMapping("/user/{email}")
    public ResponseEntity<?> updateUser(
            @PathVariable String email,
            @RequestBody @Valid RegistrationRequest request
    ) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setFirstname(request.getFirstname());
            existingUser.setLastname(request.getLastname());
            userRepository.save(existingUser);
            return ResponseEntity.ok("Actualizado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email no encontrado " + email);
        }
    }

    @DeleteMapping("/user/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return ResponseEntity.ok("Usuario borrado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email no encontrado " + email);
        }
    }
}
