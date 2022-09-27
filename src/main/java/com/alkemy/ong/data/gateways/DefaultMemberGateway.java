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
        Member memberFounded = toModel(memberRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Member", "id", id))) ;
       memberFounded.setName(member.getName());
       memberFounded.setFacebookUrl(member.getFacebookUrl());
       memberFounded.setInstagramUrl(member.getInstagramUrl());
       memberFounded.setLinkedinUrl(member.getLinkedinUrl());
       memberFounded.setImage(member.getImage());
       memberFounded.setDescription(member.getDescription());
        return toModel(memberRepository.save(toEntity(member)));
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
}
