package com.alkemy.ong.domain.news;

import com.alkemy.ong.domain.OngPage;


public interface NewGateway {

    void deleteById (Long id);


    OngPage<New> findAll(int pageNumber);
    New findById (Long id);
    New save (New news);
    New update (New news, Long id);
}
