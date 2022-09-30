package com.alkemy.ong.domain.news;

import com.alkemy.ong.domain.OngPage;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NewService {

    private final NewGateway newGateway;

    public void deleteById (Long id){

        newGateway.deleteById(id);
    }

    public OngPage<New> findAll(int page){
        return newGateway.findAll (page);
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

   // public Page<New> getAll(Pageable pageable) {
    //    return newGateway.findAll(pageable);
   // }
}
