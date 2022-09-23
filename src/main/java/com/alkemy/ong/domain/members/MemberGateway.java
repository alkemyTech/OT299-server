package com.alkemy.ong.domain.members;

import java.util.List;

public interface MemberGateway {

    List<Member> findAll();

    public void deleteById (Long id);

    public Member save(Member members);

    public Member update (Member members,Long id);
}
