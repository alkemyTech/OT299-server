package com.alkemy.ong.data.repositories;


import com.alkemy.ong.data.entities.MemberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends CrudRepository<MemberEntity, Long> {

}
