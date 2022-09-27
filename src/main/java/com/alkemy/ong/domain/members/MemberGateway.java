package com.alkemy.ong.domain.members;

import java.util.List;

public interface MemberGateway {

    List<Member> findAll();

    public void deleteById (Long id);

    public Member save(Member member);

    public Member update (Member member,Long id);
}
