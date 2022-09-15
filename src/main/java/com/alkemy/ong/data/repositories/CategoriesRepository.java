package com.alkemy.ong.data.repositories;

import com.alkemy.ong.data.entities.CategoriesEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoriesRepository extends CrudRepository<CategoriesEntity, Long> {
        List<CategoriesEntity> findAll();
}
