package com.proyectopulmonverde.userservice.Services;

import com.proyectopulmonverde.userservice.Entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {

    User saveUser(User user);
    User findUserById(User userId);
    List<User> findAllUser();
    User updateUserById(User userId);
    User deleteUserById(User UserId);

}
