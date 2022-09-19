package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.SlideEntity;
import com.alkemy.ong.data.repositories.SlideRepository;
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



    private Slide toModel(SlideEntity slideEntity) {
        return Slide.builder().id(slideEntity.getId())
                .imageUrl(slideEntity.getImageUrl())
                .slideOrder(slideEntity.getSlideOrder())
                .slideText(slideEntity.getSlideText())
                .createdAt(slideEntity.getCreatedAt())
                .updatedAt(slideEntity.getUpdatedAt())
                .deleted(slideEntity.isDeleted())
                .build();

    /*private Slide toModel(SlideEntity slideEntity) {
        return new Slide(slideEntity.getId(),
                slideEntity.getImageUrl(),
                slideEntity.getSlideText(),
                slideEntity.getSlideOrder(),
                slideEntity.getCreatedAt(),
                slideEntity.getUpdatedAt(),
                slideEntity.isDeleted());*/
    }
}
