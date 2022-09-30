package com.alkemy.ong.domain.comments;

import com.alkemy.ong.domain.categories.Categories;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class CommentService {

    CommentGateway commentGateway;

    public List<Comment> findAll(){
        return commentGateway.findAll();
    }

    public Comment createComment(Comment comment) {
        return commentGateway.createComment(comment);
    }

    public Comment updateComment(Long id, Comment comment) {
        return commentGateway.updateComment(id ,comment);
    }

    public void deleteById(Long id){
        commentGateway.deleteById(id);
    }
}
