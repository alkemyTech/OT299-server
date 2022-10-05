package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.CommentEntity;
import com.alkemy.ong.data.repositories.CommentRepository;
import com.alkemy.ong.data.repositories.NewRepository;
import com.alkemy.ong.domain.comments.Comment;
import com.alkemy.ong.domain.comments.CommentGateway;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import com.alkemy.ong.domain.news.New;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Builder
public class DefaultCommentsGateway implements CommentGateway {

    private final CommentRepository commentRepository;
    private final NewRepository newRepository;
    private final DefaultNewGateway defaultNewGateway;


    @Override
    public List<Comment> findAll() {
        return commentRepository.findByOrderByCreatedAt().stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    public Comment createComment(Comment comment) {
        return toModel(commentRepository.save(toEntity(comment)));
    }

    @Override
    public List<Comment> findByNewsId(Long newsId) {
        New post = defaultNewGateway.toModel(newRepository.findById(newsId).orElseThrow(() ->
                new ResourceNotFoundException("New", "id", newsId)));
        return commentRepository.findByNewsId(newsId).stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        Comment comment = toModel(commentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", id)));
        commentRepository.deleteById(comment.getId());
    }

    @Override
    public Comment updateComment(Long id, Comment comment) {
        CommentEntity commentEntity = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
        return toModel(updateCommentMapper(commentEntity, comment));
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

    private CommentEntity toEntity (Comment comment) {
        return CommentEntity.builder()
                .userId(comment.getUserId())
                .body(comment.getBody())
                .newsId(comment.getNewsId())
                .updatedAt(comment.getUpdatedAt())
                .createdAt(comment.getCreatedAt())
                .deleted(comment.isDeleted())
                .build();
    }

    private CommentEntity updateCommentMapper(CommentEntity commentEntity, Comment comment) {
        commentEntity.setBody(comment.getBody());
        return commentRepository.save(commentEntity);
    }
}
