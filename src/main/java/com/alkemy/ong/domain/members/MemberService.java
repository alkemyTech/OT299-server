package com.alkemy.ong.domain.members;

import com.alkemy.ong.domain.OngPage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class MemberService {

    private final MemberGateway memberGateway;

      public OngPage<Member> findAll(int pageNumber) {

        return memberGateway.findAll(pageNumber);
    }

    public void deleteById(Long id) {
          memberGateway.deleteById(id);
    }

    public Member create (Member members){
          return memberGateway.save(members);
    }

    public Member update (Member member, Long id){

          return memberGateway.update(member, id);
    }
}
