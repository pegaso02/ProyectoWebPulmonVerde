package com.proyectopulmonverde.userservice.Service;

import com.proyectopulmonverde.userservice.Entities.Article;
import com.proyectopulmonverde.userservice.Repository.ArticleRepository;
import com.proyectopulmonverde.userservice.Security.ArticleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class articleService {

   public final ArticleRepository articleRepository;

    public List<Article> findAllArticles(){
        return articleRepository.findAll();
    }

    public Optional<Article> findArticleById(Long id){
        return articleRepository.findById(id);
    }

    public void updateArticle(Article article){
        Article article1 = articleRepository.findById(article.getId()).orElseThrow(()-> new RuntimeException("articulo no encontrado"));
        article1.setContent(article.getContent());
        article1.setTitle(article.getTitle());
        articleRepository.save(article);
    }

    public Article saveArticle(ArticleRequest articleRequest){
        Article article = new Article();
        article.setTitle(articleRequest.getTitle());
        article.setContent(articleRequest.getContent());
        article.setDate(articleRequest.getDate());
        return articleRepository.save(article);
    }

    public void deleteArticleById(Long id){
        articleRepository.deleteById(id);
    }


}
