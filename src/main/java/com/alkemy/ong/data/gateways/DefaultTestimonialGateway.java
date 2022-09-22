package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.TestimonialEntity;
import com.alkemy.ong.data.repositories.TestimonialRepository;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import com.alkemy.ong.domain.testimonials.Testimonial;
import com.alkemy.ong.domain.testimonials.TestimonialGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DefaultTestimonialGateway implements TestimonialGateway {
    private final TestimonialRepository testimonialRepository;


    @Override
    public void deleteById(Long id) {
        Testimonial testimonial = toModel(testimonialRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Testimonial", "id", id)));
        testimonialRepository.deleteById(testimonial.getId());
    }

    private Testimonial toModel(TestimonialEntity entity){
        return Testimonial.builder().id(entity.getId()).content(entity.getContent())
                .name(entity.getName()).image(entity.getImage()).updatedAt(entity.getUpdatedAt())
                .createdAt(entity.getCreatedAt()).deleted(entity.isDeleted()).build();
    }

}
