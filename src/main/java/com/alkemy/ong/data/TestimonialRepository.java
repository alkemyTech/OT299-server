package com.alkemy.ong.data;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TestimonialRepository extends CrudRepository<TestimonialEntity, Long> {

    List<TestimonialEntity> findAll();
}
