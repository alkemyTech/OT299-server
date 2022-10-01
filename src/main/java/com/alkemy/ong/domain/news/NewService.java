package com.alkemy.ong.domain.news;

import com.alkemy.ong.domain.OngPage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NewService {

    private final NewGateway newGateway;

    public void deleteById (Long id){

        newGateway.deleteById(id);
    }

    public OngPage<New> findAll(int pageNumber){
        return newGateway.findAll(pageNumber);
    }

    public New findById( Long id){

        return newGateway.findById(id);
    }
    public New create (New news){

        return newGateway.save(news);
    }
    public New update (New news, Long id){

        return newGateway.update(news, id);
    }
}
