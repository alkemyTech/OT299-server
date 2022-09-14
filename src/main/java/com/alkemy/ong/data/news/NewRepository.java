package com.alkemy.ong.data.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewRepository extends JpaRepository<NewEntity, Long> {

    List<NewEntity> findAll();

}
