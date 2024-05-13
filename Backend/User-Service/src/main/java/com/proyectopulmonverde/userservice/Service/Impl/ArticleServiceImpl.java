package com.proyectopulmonverde.userservice.Service.Impl;

import com.proyectopulmonverde.userservice.Entities.Article;
import com.proyectopulmonverde.userservice.Repository.ArticleRepository;
import com.proyectopulmonverde.userservice.Service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {

    ArticleRepository articleRepository;

    @Override
    public Optional<Article> getById(Long ArticleId) {

        try{
            articleRepository.findById(ArticleId);
        }catch (UsernameNotFoundException usernameNotFoundException){
            throw new UsernameNotFoundException("Usuario con id"+" no encontrado");
        }

        return articleRepository.findById(ArticleId);
    }

    @Override
    public List<Article> getAllArticle() {
        return articleRepository.findAll();
    }

    @Override
    public List<Article> getByUserId(Long userId) {
        return articleRepository.findByUserId(userId);
    }

    @Override
    public Article createArticle(Article article) {
        articleRepository.save(article);
        return article;
    }

    @Override
    public void updateArticle(Long id, Article article) {
       Article existArticle = articleRepository.findById(id)
               .orElseThrow(()-> new InvalidParameterException("Invalid article id"));

       existArticle.setTittle(article.getTittle());
       existArticle.setContent(article.getContent());
       existArticle.setUser(article.getUser());

       articleRepository.save(existArticle);
    }

    @Override
    public void deleteArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(()-> new InvalidParameterException("Invalid article id"));
        articleRepository.delete(article);
    }

    @Override
    public List<Article> findArticleByTittle(String tittle) {
        return findArticleByTittle(tittle);
    }
}
