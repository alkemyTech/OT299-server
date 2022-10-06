package com.alkemy.ong.web;

import com.alkemy.ong.domain.activities.Activity;
import com.alkemy.ong.domain.comments.Comment;
import com.alkemy.ong.domain.comments.CommentService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@PreAuthorize("hasRole('ROLE_1')")
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PreAuthorize("permitAll()")
    @GetMapping
    public ResponseEntity<List<CommentDto>> findAll(){
        return ResponseEntity.ok()
                .body(commentService.findAll().stream()
                        .map(this::toDto)
                        .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<CommentCreateDto> createComment(@Valid @RequestBody CommentCreateDto commentDto){
        Comment comment = commentService.createComment(toModelForCreate(commentDto));
        return new ResponseEntity<>(toDtoForCreate(comment), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        Comment comment = commentService.updateComment(id, toModel(commentDto));
        return new ResponseEntity<>(toDto(comment), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteComment(@PathVariable Long id) {
        commentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private CommentDto toDto(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .body(comment.getBody()).build();
    }

    public CommentCreateDto toDtoForCreate(Comment comment){
        return CommentCreateDto.builder()
                .id(comment.getId())
                .postId(comment.getNewsId())
                .userId(comment.getUserId())
                .body(comment.getBody()).build();
    }

    private Comment toModelForCreate(CommentCreateDto commentCreateDto){

        return Comment.builder()
                .newsId(commentCreateDto.getPostId())
                .userId(commentCreateDto.getUserId())
                .body(commentCreateDto.getBody()).build();
    }

    private Comment toModel(CommentDto commentDto){
        return Comment.builder()
                .body(commentDto.getBody()).build();
    }


    @Getter
    @Setter
    @Builder
    public static class CommentCreateDto{

        @Min(value = 1, message = "The field postId cannot be null or less than 1.")
        private Long id;

        @Min(value = 1, message = "The field postId cannot be null or less than 1.")
        private long postId;

        @Min(value = 1, message = "The field userId cannot be null or less than 1.")
        private long userId;

        @NotEmpty(message = "The field body cannot be null or empty.")
        private String body;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class CommentDto{

        public CommentDto() {
        }

        private Long id;

        @NotEmpty(message = "The field body cannot be null or empty.")
        private String body;
    }
}
