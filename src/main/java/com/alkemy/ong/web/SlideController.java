package com.alkemy.ong.web;

import com.alkemy.ong.domain.slides.Slide;
import com.alkemy.ong.domain.slides.SlideService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/slides")
@AllArgsConstructor
public class SlideController {

    SlideService slideService;


    @GetMapping()
    public ResponseEntity<List<SlideDto>> findAll() {
        return ResponseEntity.ok()
                .body(slideService.findAll().stream().map(this::toDto).collect(toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Slide> findById(@PathVariable Long id) {
        return ResponseEntity.ok(slideService.findById(id));
    }

    @DeleteMapping("/{id}")
        public ResponseEntity deleteSlide(@PathVariable Long id) {
        slideService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SlideDto> updateSlide(@PathVariable Long id, @Valid @RequestBody SlideDto slideDto) {
        Slide slide = slideService.updateById(id, toModel(slideDto));
        return ResponseEntity.ok(toDto(slide));
    }

    private Slide toModel (SlideDto slideDto) {
        return Slide.builder()
                .id(slideDto.id)
                .imageUrl(slideDto.imageUrl)
                .slideText(slideDto.slideText)
                .slideOrder(slideDto.slideOrder)
                .build();
    }
    private SlideDto toDto (Slide slide) {
        return SlideDto.builder().id(slide.getId())
                .imageUrl(slide.getImageUrl())
                .slideText(slide.getSlideText())
                .slideOrder(slide.getSlideOrder())
                .build();
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
