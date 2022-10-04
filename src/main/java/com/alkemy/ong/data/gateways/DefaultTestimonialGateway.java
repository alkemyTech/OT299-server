package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.TestimonialEntity;
import com.alkemy.ong.data.repositories.TestimonialRepository;
import com.alkemy.ong.domain.OngPage;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import com.alkemy.ong.domain.testimonials.Testimonial;
import com.alkemy.ong.domain.testimonials.TestimonialGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import java.util.List;
import static java.util.stream.Collectors.toList;


@Component
@RequiredArgsConstructor
public class DefaultTestimonialGateway implements TestimonialGateway {
    private final TestimonialRepository repository;

    @Value("${pagination.pageSize}")
    private int PAGE_SIZE;

    @Override
    public OngPage<Testimonial> findAll(int pageNumber) {
        Page<TestimonialEntity> testimonialEntityPage = repository.findAll(PageRequest.of(pageNumber, PAGE_SIZE));
        List<Testimonial> testimonials = testimonialEntityPage.getContent().stream().map(this::toModel).collect(toList());
        return new OngPage.OngPageBuilder<Testimonial>("testimonials")
                .body(testimonials)
                .pageNumber(pageNumber)
                .totalPages(testimonialEntityPage.getTotalPages())
                .build();
    }

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
        TestimonialEntity testimonialEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Testimonial", "id", id));
        return toModel(updateTestimonial(testimonialEntity, testimonial));

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

    private TestimonialEntity updateTestimonial(TestimonialEntity testimonialEntity, Testimonial testimonial) {
        testimonialEntity.setName(testimonial.getName());
        testimonialEntity.setContent(testimonial.getContent());
        testimonialEntity.setImage(testimonial.getImage());
        return repository.save(testimonialEntity);
    }

}
