package com.alkemy.ong.data.repositories;

import com.alkemy.ong.data.entities.MemberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends CrudRepository<MemberEntity, Long> {
       List<MemberEntity> findAll();
}
