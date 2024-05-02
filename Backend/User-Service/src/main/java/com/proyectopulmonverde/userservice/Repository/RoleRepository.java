package com.proyectopulmonverde.userservice.Repository;

import com.proyectopulmonverde.userservice.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository <Role, Long> {

    Optional<Role> findByName(String role);
}
