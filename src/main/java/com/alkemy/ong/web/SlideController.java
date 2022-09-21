package com.alkemy.ong.web;

import com.alkemy.ong.domain.slides.Slide;
import com.alkemy.ong.domain.slides.SlideService;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/slides")
@AllArgsConstructor
public class SlideController {

    SlideService slideService;

    @GetMapping()
    public List<SlideDto> findAll() {
        return slideService.findAll().stream().map(this::toDto).collect(toList());
    }

    private SlideDto toDto (Slide slide) {
        return SlideDto.builder().id(slide.getId())
                .imageUrl(slide.getImageUrl())
                .slideText(slide.getSlideText())
                .slideOrder(slide.getSlideText())
                .build();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SlideDto {
        private Long id;
        private String imageUrl;
        private String slideText;
        private String slideOrder;
    }
}
