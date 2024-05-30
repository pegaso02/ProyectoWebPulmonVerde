package com.proyectopulmonverde.userservice.Controller;
import com.proyectopulmonverde.userservice.Entities.Article;
import com.proyectopulmonverde.userservice.Security.ArticleRequest;
import com.proyectopulmonverde.userservice.Service.articleService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/articles")
@Slf4j
public class articleController {



    private final articleService service;

    @PostMapping("/save")
    public ResponseEntity<Article>saveArticle(@Valid @RequestBody ArticleRequest articleRequest){
            Article savedArticle = service.saveArticle(articleRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
        }





    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article){
        service.updateArticle(article);
        return ResponseEntity.ok().body(article);
    }

    @GetMapping
    public List<Article> getAllArticles(){
        return service.findAllArticles();
    }

    @GetMapping("/{id}")
    public Optional<Article> getArticleById(@PathVariable Long id){
        return service.findArticleById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteArticleById(@PathVariable Long id){
        service.deleteArticleById(id);
    }
}
