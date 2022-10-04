package com.alkemy.ong.domain.testimonials;

import com.alkemy.ong.domain.OngPage;

public interface TestimonialGateway {
    void deleteById(Long id);

    Testimonial create(Testimonial testimonial);

    Testimonial update(Long id, Testimonial testimonial);

    OngPage<Testimonial> findAll(int pageNumber);
}