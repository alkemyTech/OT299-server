package com.alkemy.ong.domain.members;

import com.alkemy.ong.data.DefaultMemberGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DefaultMemberService implements MemberGateway {
    @Autowired
    DefaultMemberGateway defaultMemberGateway;

    @Override
    public List<MemberModel> findMember() {

        return defaultMemberGateway.findMember();
    }
}
