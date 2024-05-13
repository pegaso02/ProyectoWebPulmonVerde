package com.proyectopulmonverde.userservice.Service;

import com.proyectopulmonverde.userservice.Entities.Comment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CommentService {

    Optional<Comment>getCommentById(Long id);
    List<Comment>getAllComment();
    void createComment(Comment comment);
    void updateComment(Long id,Comment comment);
    void deleteComment(Long id);

}
