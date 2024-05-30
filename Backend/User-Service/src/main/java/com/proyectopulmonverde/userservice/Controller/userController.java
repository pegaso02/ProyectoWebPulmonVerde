package com.proyectopulmonverde.userservice.Controller;
import com.proyectopulmonverde.userservice.Entities.User;
import com.proyectopulmonverde.userservice.Service.userService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class userController {

    public final userService UserService;

    @PostMapping
    public ResponseEntity<User>saveUser(@RequestBody User user){
        User savedUser = UserService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User>updateUser(@PathVariable Long id, @RequestBody User user){
        UserService.updateUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public void DeleteUser(@PathVariable Long id){
        UserService.deleteUser(id);
    }

    public List<User> getAllUsers(){
        return UserService.findAllUser();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        return UserService.findUserById(id);
    }

}
