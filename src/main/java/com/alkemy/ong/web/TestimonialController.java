package com.alkemy.ong.web;

import com.alkemy.ong.domain.OngPage;
import com.alkemy.ong.domain.testimonials.Testimonial;
import com.alkemy.ong.domain.testimonials.TestimonialService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(
                    mediaType = "application/json", examples = {@ExampleObject(name= "errors",
                    value = "{error: [Internal Server Error]}")})})
    })
    public ResponseEntity<OngPage<Testimonial>> pageAll(@RequestParam Integer page) {
        OngPage<Testimonial> pageTestimonials = testimonialService.findAll(page);
        return ResponseEntity.ok(pageTestimonials);
    }

    @DeleteMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {@Content(
                    mediaType = "application/json", examples = {@ExampleObject(name= "errors",
                    value = "error: Testimonial not found with: id :")})}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(
                    mediaType = "application/json", examples = {@ExampleObject(name= "errors",
                    value = "{error: [Internal Server Error]}")})})
    })
    public ResponseEntity<Void> delete(@RequestParam(name = "id") Long id){
        testimonialService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(
                    mediaType = "application/json", examples = {@ExampleObject(name= "errors",
                    value = "{errors: [Name is required],  [Content is required],  [Image is required]}")})}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(
                    mediaType = "application/json", examples = {@ExampleObject(name= "errors",
                    value = "{errors: [Invalid Input]}")})}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(
                    mediaType = "application/json", examples = {@ExampleObject(name= "errors",
                    value = "{error: [Internal Server Error]}")})})
    })
    public ResponseEntity<TestimonialDto> create(@Valid @RequestBody TestimonialDto testimonialDto)  {
        Testimonial testimonial = testimonialService.create(toModel(testimonialDto));
        return new ResponseEntity<>(toDto(testimonial), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(
                    mediaType = "application/json", examples = {@ExampleObject(name= "errors",
                    value = "{errors: [Invalid Input]}")})}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {@Content(
                    mediaType = "application/json", examples = {@ExampleObject(name= "errors",
                    value = "error: Testimonial not found with: id :")})}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(
                    mediaType = "application/json", examples = {@ExampleObject(name= "errors",
                    value = "{error: [Internal Server Error]}")})})
    })
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
