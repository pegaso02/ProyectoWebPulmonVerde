package com.proyectopulmonverde.userservice.Service.Impl;

import com.proyectopulmonverde.userservice.Entities.User;
import com.proyectopulmonverde.userservice.Repository.UserRepository;
import com.proyectopulmonverde.userservice.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public User FindByEmail(String email) {
        return userRepository.FindByEmail(email);
    }


}
