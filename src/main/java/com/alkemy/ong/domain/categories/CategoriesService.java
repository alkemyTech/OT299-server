package com.alkemy.ong.domain.categories;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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

    //public Categories createByName(String name) {
       //return categoriesGateway.createByName(name);
   // }

    public void deleteById(Long id) {
        categoriesGateway.deleteById(id);
    }


}