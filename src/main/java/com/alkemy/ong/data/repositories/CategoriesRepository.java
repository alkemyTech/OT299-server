package com.alkemy.ong.data.repositories;

import com.alkemy.ong.data.entities.CategoriesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriesRepository extends CrudRepository<CategoriesEntity, Long> {

        List<CategoriesEntity> findAll();

        Optional<CategoriesEntity> findById(long id);

        CategoriesEntity save(String name);

        void deleteById(Long id);

}
