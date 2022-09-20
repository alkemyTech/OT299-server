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
}
