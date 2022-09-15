package com.alkemy.ong.data.Repositories;

import com.alkemy.ong.data.Entities.CategoriesEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoriesRepository extends CrudRepository<CategoriesEntity, Long> {
        List<CategoriesEntity> findAll();
}
