package com.alkemy.ong.domain.news;

import com.alkemy.ong.domain.OngPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewGateway {

    public void deleteById (Long id);


    OngPage<New> findAll(int page);
    New findById (Long id);
    public New save (New news);
    public New update (New news, Long id);
}
