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
    private Member toModel(MemberEntity memberEntity){
        return  Member.builder()
                .id(memberEntity.getId())
                .name(memberEntity.getName())
                .facebookUrl(memberEntity.getFacebookUrl())
                .instagramUrl(memberEntity.getInstagramUrl())
                .linkedinUrl(memberEntity.getLinkedinUrl())
                .image(memberEntity.getImage())
                .description(memberEntity.getDescription())
                .createdAt(memberEntity.getCreatedAt())
                .updatedAt(memberEntity.getUpdatedAt())
                .deleted(memberEntity.isDeleted())
                .build();
    }
}
