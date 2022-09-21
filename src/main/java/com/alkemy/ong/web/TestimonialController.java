package com.alkemy.ong.web;

import com.alkemy.ong.domain.testimonials.TestimonialService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/testimonials")
@AllArgsConstructor
public class TestimonialController {

    private final TestimonialService testimonialService;

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam(name = "id") Long id){
        testimonialService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
