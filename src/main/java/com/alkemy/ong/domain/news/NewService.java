package com.alkemy.ong.domain.news;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NewService {

    private final NewGateway newGateway;

    public void deleteById (Long id){

        newGateway.deleteById(id);
    }

    public List<New> findAll(){
        return newGateway.findAll();
    }

    public New findById( Long id){

        return newGateway.findById(id);
    }
    public New update (New news, Long id){
        return newGateway.update(news, id);
    }
}
