package com.proyectopulmonverde.userservice.Service;

import com.proyectopulmonverde.userservice.Entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User>getUserById(Long id);
    List<User>getAllUser();
    Optional<User>getUserByUsername(String username);
    User FindByEmail(String email);

}
