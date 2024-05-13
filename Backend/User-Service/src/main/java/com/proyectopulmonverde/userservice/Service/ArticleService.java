package com.proyectopulmonverde.userservice.Service;

import com.proyectopulmonverde.userservice.Entities.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    Optional<Article> getById(Long ArticleId);
    List<Article> getAllArticle();
    List<Article> getByUserId(Long userId);
    public Article createArticle(Article article);
    public void updateArticle(Long id, Article article);
    public void deleteArticle(Long id);
    List<Article>findArticleByTittle(String tittle);
}
