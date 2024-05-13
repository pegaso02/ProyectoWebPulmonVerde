package com.proyectopulmonverde.userservice.Controller;

import com.proyectopulmonverde.userservice.Entities.Article;
import com.proyectopulmonverde.userservice.Entities.User;
import com.proyectopulmonverde.userservice.Service.Impl.ArticleServiceImpl;
import com.proyectopulmonverde.userservice.Service.Impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/article")
public class ArticleController {

    ArticleServiceImpl articleService;
    UserServiceImpl userService;
    @GetMapping("/home")
    public String homePage(Model model){
        model.addAttribute("post",articleService.getAllArticle());
        return "/article/home";
    }

    @PostMapping("/create")
    public ResponseEntity<Article> createArticle(Article article, Authentication authentication){
        String email = authentication.getName();
        User user = userService.FindByEmail(email);

        article.setCreatedBy(user);

        Article article1 = articleService.createArticle(article);
        // Crear URI del nuevo recurso
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(article1.getId())
                .toUri();
        return ResponseEntity.created(location).body(article1);

    }
}
