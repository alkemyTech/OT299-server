package com.alkemy.ong.domain.members;

import com.alkemy.ong.domain.OngPage;

import java.util.List;

public interface MemberGateway {

    public OngPage<Member> findAll(int pageNumber);

    public void deleteById (Long id);

    public Member save(Member member);

    public Member update (Member member,Long id);
}
