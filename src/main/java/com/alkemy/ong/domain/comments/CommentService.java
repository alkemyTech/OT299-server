package com.alkemy.ong.domain.comments;

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

    public void deleteById(Long id){
        commentGateway.deleteById(id);
    }
}
