package com.alkemy.ong.data.repositories;

import com.alkemy.ong.data.entities.NewEntity;
import com.alkemy.ong.domain.news.New;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewRepository extends PagingAndSortingRepository<NewEntity, Long> {

    List<NewEntity> findAll();

    Page<NewEntity> findAll(Pageable pageable);
}
