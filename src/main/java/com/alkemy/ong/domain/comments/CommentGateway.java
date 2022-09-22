package com.alkemy.ong.domain.comments;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentGateway {

    List<Comment> findAll();

    void deleteById(Long id);
}
