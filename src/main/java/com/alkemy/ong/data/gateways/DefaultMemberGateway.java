package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.MemberEntity;
import com.alkemy.ong.data.repositories.MemberRepository;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
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

    @Override
    public void deleteById(Long id) {
        Member member = toModel(memberRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Member", "id", id)));
            memberRepository.deleteById(member.getId());

    }

    @Override
    public Member save(Member member) {
        return toModel((memberRepository.save(toEntity(member))));
    }

    @Override
    public Member update(Member member, Long id) {
        MemberEntity memberEntity = memberRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Member", "id", id)) ;
        return toModel(updateMember(memberEntity, member));
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

    private MemberEntity toEntity (Member member){
        return MemberEntity.builder().id(member.getId())
                .name(member.getName())
                .facebookUrl(member.getFacebookUrl())
                .instagramUrl(member.getInstagramUrl())
                .linkedinUrl(member.getLinkedinUrl())
                .image(member.getImage())
                .description(member.getDescription())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .deleted(member.isDeleted())
                .build();
    }
    private MemberEntity updateMember(MemberEntity memberEntity, Member member ){
        memberEntity.setName(member.getName());
        memberEntity.setFacebookUrl(member.getFacebookUrl());
        memberEntity.setInstagramUrl(member.getInstagramUrl());
        memberEntity.setLinkedinUrl(memberEntity.getLinkedinUrl());
        memberEntity.setImage(member.getImage());
        memberEntity.setDescription(member.getDescription());
        return memberRepository.save(memberEntity);
    }
}
