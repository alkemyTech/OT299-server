package com.alkemy.ong.domain.slides;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlideService {

    private final SlideGateway slideGateway;

    public SlideService(SlideGateway slideGateway) {
        this.slideGateway = slideGateway;
    }

    public List<Slide> findAll() {
        return slideGateway.findAll();
    }

    public Slide findById(Long id) {
        return slideGateway.findById(id);
    }

    public void deleteById(Long id) {
       slideGateway.deleteById(id);
    }

    public Slide updateById(Long id, Slide slide) {
        return slideGateway.updateById(id, slide);
    }
}
