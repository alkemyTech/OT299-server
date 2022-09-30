package com.alkemy.ong.domain.slides;


import java.util.List;

public interface SlideGateway {

    List<Slide> findAll();

    Slide findById(Long id);

    Slide createSlide(Slide slide);

    void deleteById(Long id);

    Slide updateById(Long id, Slide slide);

}
