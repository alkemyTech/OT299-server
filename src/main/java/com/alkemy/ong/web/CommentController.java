package com.alkemy.ong.web;

import com.alkemy.ong.domain.comments.Comment;
import com.alkemy.ong.domain.comments.CommentService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping()
    public List<CommentDto> findAll(){
        return commentService.findAll().stream().map(this::commentToDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteById(id);
    }

    private CommentDto commentToDto(Comment comment){
        return CommentDto.builder().body(comment.getBody()).build();
    }

    @Getter
    @Setter
    @Builder
    public static class CommentDto{
        private String body;
    }
}
