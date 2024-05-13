package com.proyectopulmonverde.userservice.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "content")
    private String content;
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    /*Relacion con usuarios*/
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "userId"
    )
    private User user;

    /*Relacion con articulos*/
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "articleId"
    )
    private Article article;


}
