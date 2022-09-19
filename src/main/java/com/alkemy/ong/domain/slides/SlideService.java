package com.alkemy.ong.domain.slides;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlideService {

    @Autowired
    SlideGateway slideGateway;

    public List<Slide> findAll() {
        return slideGateway.findAll();
    }

    }
