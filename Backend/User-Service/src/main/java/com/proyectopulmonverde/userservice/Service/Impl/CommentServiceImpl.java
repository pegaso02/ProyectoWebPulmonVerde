package com.proyectopulmonverde.userservice.Service.Impl;

import com.proyectopulmonverde.userservice.Entities.Comment;
import com.proyectopulmonverde.userservice.Repository.CommentRepository;
import com.proyectopulmonverde.userservice.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    CommentRepository commentRepository;

    @Override
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    @Override
    public void createComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void updateComment(Long id, Comment comment) {
        Comment comment1 = getCommentById(id).orElseThrow(()-> new InvalidParameterException("Invalid comment id"));
        comment1.setContent(comment.getContent());
        commentRepository.save(comment1);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
