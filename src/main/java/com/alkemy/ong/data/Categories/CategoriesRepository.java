package com.alkemy.ong.data.Categories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriesRepository extends JpaRepository <CategoriesEntity, Long>{
        List<CategoriesEntity> findAll();
}
