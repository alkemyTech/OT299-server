package com.alkemy.ong.data.repositories;

import com.alkemy.ong.data.entities.CommentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<CommentEntity, Long> {

    List<CommentEntity> findByOrderByCreatedAt();

    void deleteById(Long id);

    List<CommentEntity> findByNewsId(long newsId);
}
