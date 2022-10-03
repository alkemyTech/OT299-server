package com.alkemy.ong.data.repositories;

import com.alkemy.ong.data.entities.CategoriesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoriesRepository extends CrudRepository<CategoriesEntity, Long> {

        Page<CategoriesEntity> findAll(Pageable pageable);

        Optional<CategoriesEntity> findById(long id);

        void deleteById(Long id);

}
