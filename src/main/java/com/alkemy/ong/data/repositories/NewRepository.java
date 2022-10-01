package com.alkemy.ong.data.repositories;

import com.alkemy.ong.data.entities.NewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewRepository extends PagingAndSortingRepository<NewEntity, Long> {

    Page<NewEntity> findAll(Pageable pageable);
}
