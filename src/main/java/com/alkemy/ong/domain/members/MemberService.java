package com.alkemy.ong.domain.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MemberService {
    @Autowired
    MemberGateway memberGateway;

      public List<Member> findAll() {

        return memberGateway.findAll();
    }
}
