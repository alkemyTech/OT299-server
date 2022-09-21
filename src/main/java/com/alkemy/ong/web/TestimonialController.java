package com.alkemy.ong.web;

import com.alkemy.ong.domain.testimonials.TestimonialService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testimonials")
@AllArgsConstructor
public class TestimonialController {

    private final TestimonialService testimonialService;

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id){
        testimonialService.deleteTestimonial(id);
        //return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
