package com.alkemy.ong.data.repositories;

import com.alkemy.ong.data.entities.CategoriesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepository extends CrudRepository<CategoriesEntity, Long> {
        List<CategoriesEntity> findAll();

}
