package com.alkemy.ong.data.repositories;

import com.alkemy.ong.data.entities.TestimonialEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TestimonialRepository extends CrudRepository<TestimonialEntity, Long> {

    List<TestimonialEntity> findAll();
}
