package com.proyectopulmonverde.userservice.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(length = 10)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Role role;



}
