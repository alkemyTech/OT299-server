package com.alkemy.ong.domain.testimonials;

import org.springframework.stereotype.Service;

@Service
public class TestimonialService {

    private final TestimonialGateway testimonialGateway;

    public TestimonialService(TestimonialGateway testimonialGateway) {
        this.testimonialGateway = testimonialGateway;
    }

    public void deleteById(Long id){
        testimonialGateway.deleteById(id);
    }

    public Testimonial create(Testimonial testimonial) {
        return testimonialGateway.create(testimonial);
    }
}
