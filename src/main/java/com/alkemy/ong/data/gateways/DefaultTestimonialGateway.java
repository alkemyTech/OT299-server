package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.TestimonialEntity;
import com.alkemy.ong.data.repositories.TestimonialRepository;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import com.alkemy.ong.domain.testimonials.Testimonial;
import com.alkemy.ong.domain.testimonials.TestimonialGateway;
import com.alkemy.ong.web.TestimonialController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DefaultTestimonialGateway implements TestimonialGateway {
    private final TestimonialRepository repository;


    @Override
    public void deleteById(Long id) {
        Testimonial testimonial = toModel(repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Testimonial", "id", id)));
        repository.deleteById(testimonial.getId());
    }

    @Override
    public Testimonial create(Testimonial testimonial) {
        return toModel(repository.save(toEntity(testimonial)));
    }

    @Override
    public Testimonial update(Long id, Testimonial testimonial) {
        Testimonial testimonialFound = toModel(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Testimonial", "id", id)));
        testimonialFound.setName(testimonial.getName());
        testimonialFound.setContent(testimonial.getContent());
        testimonialFound.setImage(testimonial.getImage());
        return toModel(repository.save(toEntity(testimonialFound)));
    }

    private Testimonial toModel(TestimonialEntity entity){
        return Testimonial.builder().id(entity.getId()).content(entity.getContent())
                .name(entity.getName()).image(entity.getImage()).updatedAt(entity.getUpdatedAt())
                .createdAt(entity.getCreatedAt()).deleted(entity.isDeleted()).build();
    }

    private TestimonialEntity toEntity(Testimonial testimonial) {
        return TestimonialEntity.builder().id(testimonial.getId()).content(testimonial.getContent())
                .name(testimonial.getName()).image(testimonial.getImage()).updatedAt(testimonial.getUpdatedAt())
                .createdAt(testimonial.getCreatedAt()).deleted(testimonial.isDeleted()).build();
    }
}
