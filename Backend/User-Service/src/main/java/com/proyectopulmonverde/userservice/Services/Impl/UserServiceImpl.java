package com.proyectopulmonverde.userservice.Services.Impl;

import com.proyectopulmonverde.userservice.Entities.User;
import com.proyectopulmonverde.userservice.Repository.RoleRepository;
import com.proyectopulmonverde.userservice.Repository.UserRepository;
import com.proyectopulmonverde.userservice.Services.Exceptions.UserAlreadyExistsException;
import com.proyectopulmonverde.userservice.Services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User saveUser(User user) {
        String email = user.getEmail();
        if (userRepository.existByEmail(email)){
            throw new UserAlreadyExistsException();
        }
        user.setRole(roleRepository.findByName("ROLE_CLIENT"));




        return null;
    }

    @Override
    public User findUserById(User userId) {
        return null;
    }

    @Override
    public List<User> findAllUser() {
        return null;
    }

    @Override
    public User updateUserById(User userId) {
        return null;
    }

    @Override
    public User deleteUserById(User UserId) {
        return null;
    }
}
