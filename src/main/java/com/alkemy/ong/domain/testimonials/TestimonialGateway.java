package com.alkemy.ong.domain.testimonials;

public interface TestimonialGateway {
    void deleteById(Long id);
    Testimonial create(Testimonial testimonial);
    Testimonial update(Long id, Testimonial testimonial);
}
