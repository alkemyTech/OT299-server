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
        return new Slide(slideEntity.getId(),
                slideEntity.getImageUrl(),
                slideEntity.getSlideText(),
                slideEntity.getSlideOrder(),
                slideEntity.getCreatedAt(),
                slideEntity.getUpdatedAt(),
                slideEntity.isDeleted());
    }
}
