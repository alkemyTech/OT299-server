package com.alkemy.ong.data.repositories;

import com.alkemy.ong.data.entities.SlideEntity;
import com.alkemy.ong.domain.slides.Slide;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlideRepository extends CrudRepository<SlideEntity, Long> {

    List<SlideEntity> findAll();

    @Override
    void deleteById(Long id);
}
