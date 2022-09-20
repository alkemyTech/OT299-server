package com.alkemy.ong.web;

import com.alkemy.ong.data.gateways.DefaultSlideGateway;
import com.alkemy.ong.domain.slides.Slide;
import com.alkemy.ong.domain.slides.SlideService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/slides")
@AllArgsConstructor
public class SlideController {

    SlideService slideService;

    DefaultSlideGateway defaultSlideGateway;

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

    @DeleteMapping("/{id}")
    public void deleteSlide(@PathVariable Long id) {
        defaultSlideGateway.deleteById(id);
    }


    @Getter
    @Setter
    @Builder
    public static class SlideDto {
        private Long id;
        private String imageUrl;
        private String slideText;
        private String slideOrder;
    }
}
