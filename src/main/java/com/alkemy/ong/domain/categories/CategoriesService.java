package com.alkemy.ong.domain.categories;

import com.alkemy.ong.domain.OngPage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoriesService {

    CategoriesGateway categoriesGateway;

    public OngPage<Categories> findAll(int pageNumber){
        return categoriesGateway.findAll(pageNumber);
    }

    public Categories findById(long id){
       return categoriesGateway.findById(id);
    }

    public Categories createCategory(Categories categories) {
        return categoriesGateway.createCategory(categories);
    }

    public Categories updateCategory(Long id, Categories categories) {
        return categoriesGateway.updateCategory(id ,categories);
    }

    public void deleteById(Long id) {
        categoriesGateway.deleteById(id);
    }

}