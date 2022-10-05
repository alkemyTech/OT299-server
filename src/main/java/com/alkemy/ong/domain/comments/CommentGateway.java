package com.alkemy.ong.domain.comments;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentGateway {

    List<Comment> findAll();

    Comment createComment(Comment comment);

    List<Comment> findByNewsId(Long NewsId);

    void deleteById(Long id);


    Comment updateComment(Long id, Comment comment);
}
