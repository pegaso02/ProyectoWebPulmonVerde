package com.proyectopulmonverde.userservice;

import com.proyectopulmonverde.userservice.Entities.Role;
import com.proyectopulmonverde.userservice.Repository.RoleRepository;
import com.proyectopulmonverde.userservice.Security.RegistrationRequest;
import com.proyectopulmonverde.userservice.Service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import static com.proyectopulmonverde.userservice.Entities.Role.*;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service
    ) {
        return args -> {
            var admin = RegistrationRequest.builder()
                    .firstname("Admin")
                    .lastname("Admin")
                    .email("AdministradorNew@gmail.com")
                    .password("password123")
                    .role(ADMIN)
                    .build();

            var authResponse = service.register(admin);
            System.out.println("Token Administrador: " + authResponse.getAccessToken());

        };

    }

}
