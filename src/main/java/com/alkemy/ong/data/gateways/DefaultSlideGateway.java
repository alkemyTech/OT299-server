package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.SlideEntity;
import com.alkemy.ong.data.repositories.SlideRepository;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import com.alkemy.ong.domain.slides.Slide;
import com.alkemy.ong.domain.slides.SlideGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class DefaultSlideGateway implements SlideGateway {

    private final SlideRepository slideRepository;


    @Override
    public List<Slide> findAll() {
        return slideRepository.findAll().stream().map(this::toModel).collect(toList());
    }

    @Override
    public Slide findById(Long id) {
        SlideEntity slideEntity = slideRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Slide", "id", id));
        return  toModel(slideEntity);
    }

    @Override
    public List<Slide> findByOrganizationId(Long organizationId) {
        List<Slide> slideListByOrganizationId = slideRepository.findAllByOrganizationId(organizationId).stream().map(this::toModel).collect(toList());
        Collections.sort(slideListByOrganizationId, (Comparator.<Slide>comparingLong(slideOne -> slideOne.getSlideOrder()).thenComparingLong(slideTwo -> slideTwo.getSlideOrder())));
        return slideListByOrganizationId;
    }

    @Override
    public Slide createSlide(Slide slide) {
        List<Slide> slideList = slideRepository.findAll().stream().map(this::toModel).collect(toList());
        if (slide.getSlideOrder() == null) {
            slide.setSlideOrder(slideList.stream().count() + 1);
        }
        return  toModel(slideRepository.save(toEntity(slide)));
    }


    @Override
     public void deleteById(Long id) {
        Slide slide = toModel(slideRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Slide", "id", id)));
        slideRepository.deleteById(slide.getId());
    }

    @Override
    public Slide updateById(Long id, Slide slide) {
        slide.setId(id);
        SlideEntity slideEntity = slideRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Slide", "id", id));
        return toModel(updateEntity(slideEntity, slide));
    }

    private Slide toModel(SlideEntity slideEntity) {
        return Slide.builder().id(slideEntity.getId())
                .imageUrl(slideEntity.getImageUrl())
                .slideOrder(slideEntity.getSlideOrder())
                .slideText(slideEntity.getSlideText())
                .organizationId(slideEntity.getOrganizationId())
                .createdAt(slideEntity.getCreatedAt())
                .updatedAt(slideEntity.getUpdatedAt())
                .deleted(slideEntity.isDeleted())
                .build();
    }

    private SlideEntity toEntity(Slide slide) {
        return SlideEntity.builder()
                .id(slide.getId())
                .imageUrl(slide.getImageUrl())
                .slideText(slide.getSlideText())
                .slideOrder(slide.getSlideOrder())
                .organizationId(slide.getOrganizationId())
                .createdAt(slide.getCreatedAt())
                .updatedAt(slide.getUpdatedAt())
                .deleted(slide.isDeleted())
                .build();
    }


    private SlideEntity updateEntity(SlideEntity slideEntity, Slide slide) {
       slideEntity.setImageUrl(slide.getImageUrl());
       slideEntity.setSlideText(slide.getSlideText());
       slideEntity.setSlideOrder(slide.getSlideOrder());
       return slideRepository.save(slideEntity);

    }



}
