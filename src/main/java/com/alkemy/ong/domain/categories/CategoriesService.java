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

    public Optional<Categories> findById(long id){
       return Optional.ofNullable(categoriesGateway.findById(id));
    }

    public void deleteById(Long id) {
        categoriesGateway.deleteById(id);
    }

}