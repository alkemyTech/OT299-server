package com.alkemy.ong.web;

import com.alkemy.ong.domain.testimonials.TestimonialService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/testimonials")
@AllArgsConstructor
public class TestimonialController {

    private final TestimonialService testimonialService;

    @DeleteMapping()
    public void delete(@RequestParam(name = "id", required = true) Long id){
        testimonialService.deleteTestimonial(id);
    }


}
