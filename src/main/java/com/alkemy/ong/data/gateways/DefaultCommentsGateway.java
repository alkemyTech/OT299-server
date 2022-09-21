package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.CommentEntity;
import com.alkemy.ong.data.repositories.CommentRepository;
import com.alkemy.ong.domain.comments.Comment;
import com.alkemy.ong.domain.comments.CommentGateway;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Builder
public class DefaultCommentsGateway implements CommentGateway {

    private final CommentRepository commentRepository;


    @Override
    public List<Comment> findAll() {
        return commentRepository.findByOrderByCreatedAt().stream().map(this::toModel).collect(Collectors.toList());
    }
    private Comment toModel (CommentEntity commentEntity) {
        return Comment.builder()
                .id(commentEntity.getId())
                .userId(commentEntity.getUserId())
                .body(commentEntity.getBody())
                .newsId(commentEntity.getNewsId())
                .updatedAt(commentEntity.getUpdatedAt())
                .createdAt(commentEntity.getCreatedAt())
                .deleted(commentEntity.isDeleted())
                .build();
    }
}
