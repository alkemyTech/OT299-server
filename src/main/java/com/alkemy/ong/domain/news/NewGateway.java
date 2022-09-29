package com.alkemy.ong.domain.news;

import java.util.List;

public interface NewGateway {

    public void deleteById (Long id);

    List<New> findAll();
    New findById (Long id);
    public New save (New news);
    public New update (New news, Long id);
}
