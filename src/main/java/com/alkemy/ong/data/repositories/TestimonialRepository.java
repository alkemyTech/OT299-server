package com.alkemy.ong.data.repositories;

import com.alkemy.ong.data.entities.TestimonialEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestimonialRepository extends CrudRepository<TestimonialEntity, Long> {

    List<TestimonialEntity> findAll();
    void deleteById(Long id);
}
