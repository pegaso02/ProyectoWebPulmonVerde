package com.proyectopulmonverde.userservice.Repository;

import com.proyectopulmonverde.userservice.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User FindByEmail(String email);

    Boolean existByEmail(String email);

}
