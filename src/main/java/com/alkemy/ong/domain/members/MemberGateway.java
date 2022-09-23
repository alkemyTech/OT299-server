package com.alkemy.ong.domain.members;

import java.util.List;

public interface MemberGateway {

    List<Member> findAll();

    void deleteById (Long id);

    Member save(Member members);
}
