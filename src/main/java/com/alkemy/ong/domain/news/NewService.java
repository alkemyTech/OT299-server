package com.alkemy.ong.domain.news;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NewService {

    private final NewGateway newGateway;

    public void deleteById (Long id){
        newGateway.deleteById(id);
    }
}
