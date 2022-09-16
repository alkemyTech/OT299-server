package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.MemberEntity;
import com.alkemy.ong.data.repositories.MemberRepository;
import com.alkemy.ong.domain.members.Member;
import com.alkemy.ong.domain.members.MemberGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.util.stream.Collectors.*;

@Component
@RequiredArgsConstructor
public class DefaultMemberGateway implements MemberGateway {
    private final  MemberRepository memberRepository;
    @Override
    public List<Member> findAll() {
        return memberRepository.findAll().stream()
                .map(this::toModel)
                .collect(toList());

    }
    private Member toModel(MemberEntity entity){
        return new Member(entity.getId(),
                entity.getName(),
                entity.getFacebookUrl(),
                entity.getInstagramUrl(),
                entity.getLinkedinUrl(),
                entity.getImage(),
                entity.getDescription(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.isDeleted());
    }
}
