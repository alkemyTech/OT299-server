package com.alkemy.ong.data;

import com.alkemy.ong.data.repositories.MemberRepository;
import com.alkemy.ong.domain.members.MemberModel;
import com.alkemy.ong.domain.members.MemberGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DefaultMemberGateway implements MemberGateway {
    @Autowired
    MemberRepository memberRepository;

    @Override
    public List<MemberModel> findMember() {

        return  StreamSupport.stream(
                        memberRepository.findAll().spliterator(), false)
                .map(x -> new MemberModel(x.getId(),
                        x.getName(),
                        x.getFacebookUrl(),
                        x.getInstagramUrl(),
                        x.getLinkedinUrl(),
                        x.getImage(),
                        x.getDescription(),
                        x.getCreatedAt(),
                        x.getUpdatedAt(),
                        x.isDeleted()))
                .collect(Collectors.toList());

    }
}
