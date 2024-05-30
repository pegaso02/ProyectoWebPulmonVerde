package com.proyectopulmonverde.userservice.Repository;

import com.proyectopulmonverde.userservice.Entities.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {


}
