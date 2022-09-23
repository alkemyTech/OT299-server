package com.alkemy.ong.domain.categories;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class CategoriesService {

    CategoriesGateway categoriesGateway;

    public List<Categories> findAll(){
        return categoriesGateway.findAll();
    }

    public Categories findById(long id){
       return categoriesGateway.findById(id);
    }

    public Categories createCategory(Categories categories) {
        return categoriesGateway.createCategory(categories);
    }

    public Categories updateCategory(Long id, Categories categories) {
        return categoriesGateway.updateCategory(id, categories);
    }

    public void deleteById(Long id) {
        categoriesGateway.deleteById(id);
    }

}
