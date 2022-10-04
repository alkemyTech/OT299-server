package com.alkemy.ong.data.repositories;

import com.alkemy.ong.data.entities.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends PagingAndSortingRepository<MemberEntity, Long> {
       Page<MemberEntity> findAll(Pageable pageable);
}
