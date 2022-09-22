package com.alkemy.ong.domain.categories;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface CategoriesGateway {

    List<Categories> findAll();

    Categories findById(long id);

    Categories createByName(String name);

    void deleteById(Long id);

}
