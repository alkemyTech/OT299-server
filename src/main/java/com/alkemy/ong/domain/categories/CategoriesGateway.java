package com.alkemy.ong.domain.categories;

import com.alkemy.ong.domain.OngPage;
import org.springframework.stereotype.Component;

@Component
public interface CategoriesGateway {

    OngPage<Categories> findAll(int pageNumber);

    Categories findById(long id);

    Categories createCategory(Categories categories);

    Categories updateCategory(Long id, Categories categories);

    void deleteById(Long id);

}
