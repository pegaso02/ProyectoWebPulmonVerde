package com.proyectopulmonverde.userservice.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tittle", length = 100)
    private String tittle;
    @Column(name = "content", length = 500)
    private String content;
    @Column(name = "createdAt")
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;


    /*Relacion con usuarios*/
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "userId"
    )
    private User user;

    /*Relacion con comentarios*/
    @OneToMany(
            mappedBy = "article",
            cascade = CascadeType.ALL

    )
    private List<Comment> comments = new ArrayList<>();
}
