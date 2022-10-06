package com.alkemy.ong.web;

import com.alkemy.ong.domain.OngPage;
import com.alkemy.ong.domain.testimonials.Testimonial;
import com.alkemy.ong.domain.testimonials.TestimonialService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/testimonials")
@AllArgsConstructor
public class TestimonialController {

    private final TestimonialService testimonialService;

    @GetMapping
    public ResponseEntity<OngPage<Testimonial>> pageAll(@RequestParam Integer page) {
        OngPage<Testimonial> pageTestimonials = testimonialService.findAll(page);
        return ResponseEntity.ok(pageTestimonials);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam(name = "id") Long id){
        testimonialService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<TestimonialDto> create(@Valid @RequestBody TestimonialDto testimonialDto)  {
        Testimonial testimonial = testimonialService.create(toModel(testimonialDto));
        return new ResponseEntity<>(toDto(testimonial), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestimonialDto> update(@PathVariable final Long id,
                                                 @Valid @RequestBody TestimonialDto testimonialDto) {
        Testimonial testimonial = testimonialService.update(id, toModel(testimonialDto));
        return new ResponseEntity<>(toDto(testimonial), HttpStatus.OK);
    }

    private Testimonial toModel(TestimonialDto testimonialDto){
        return Testimonial.builder().id(testimonialDto.id).name(testimonialDto.name).content(testimonialDto.content)
                .image(testimonialDto.image)
                .updatedAt(testimonialDto.updatedAt).createdAt(testimonialDto.createdAt).deleted(testimonialDto.deleted)
                .build();
    }

    private TestimonialDto toDto(Testimonial testimonial) {
        return TestimonialDto.builder().id(testimonial.getId()).name(testimonial.getName()).content(testimonial.getContent())
                .image(testimonial.getImage()).updatedAt(testimonial.getUpdatedAt()).createdAt(testimonial.getCreatedAt())
                .deleted(testimonial.isDeleted()).build();
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TestimonialDto {
        private Long id;
        @NotBlank(message = "Name is required")
        private String name;
        @NotBlank(message = "Content is required")
        private String content;
        @NotBlank(message = "Image is required")
        private String image;
        private LocalDateTime updatedAt;
        private LocalDateTime createdAt;
        private boolean deleted = false;
    }

}
