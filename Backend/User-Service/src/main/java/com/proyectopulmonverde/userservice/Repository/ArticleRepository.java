package com.proyectopulmonverde.userservice.Repository;

import com.proyectopulmonverde.userservice.Entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long > {
    List<Article> findByUserId(Long userId);
    List<Article>findByTittle(String tittle);


}
