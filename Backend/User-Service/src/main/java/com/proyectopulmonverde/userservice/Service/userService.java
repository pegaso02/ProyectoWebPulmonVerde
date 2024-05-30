package com.proyectopulmonverde.userservice.Service;

import com.proyectopulmonverde.userservice.Entities.User;
import com.proyectopulmonverde.userservice.Repository.UserRepository;
import com.proyectopulmonverde.userservice.Services.Exceptions.userNotFountException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RequiredArgsConstructor
@Service
public class userService {

    UserRepository userRepository;

    /*getAll, getById, save, delete, update*/

    public List<User>findAllUser(){
        return userRepository.findAll();
    }

    public Optional<User>findUserById(Long id){
        return userRepository.findById(id);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public void updateUser(User user){
        User user1 = userRepository.findById(user.getId()).orElseThrow(()-> new userNotFountException());
        user1.setFirstname(user.getFirstname());
        user1.setLastname(user.getLastname());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setPhone(user.getPhone());
        userRepository.save(user1);
    }

}
