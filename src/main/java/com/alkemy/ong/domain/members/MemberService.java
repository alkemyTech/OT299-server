package com.alkemy.ong.domain.members;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class MemberService {

    private final MemberGateway memberGateway;

      public List<Member> findAll() {

        return memberGateway.findAll();
    }

    public void deleteById(Long id) {
          memberGateway.deleteById(id);
    }

    public Member create (Member members){
          return memberGateway.save(members);
    }

    public Member update (Member members, Long id){

          return memberGateway.update(members, id);
    }
}
