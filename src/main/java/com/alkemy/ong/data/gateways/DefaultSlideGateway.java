package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.SlideEntity;
import com.alkemy.ong.data.repositories.SlideRepository;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import com.alkemy.ong.domain.slides.Slide;
import com.alkemy.ong.domain.slides.SlideGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
                .createdAt(slideEntity.getCreatedAt())
                .updatedAt(slideEntity.getUpdatedAt())
                .deleted(slideEntity.isDeleted())
                .build();
    }


    private SlideEntity updateEntity(SlideEntity slideEntity, Slide slide) {
       slideEntity.setImageUrl(slide.getImageUrl());
       slideEntity.setSlideText(slide.getSlideText());
       slideEntity.setSlideOrder(slide.getSlideOrder());
       return slideRepository.save(slideEntity);

    }



}
