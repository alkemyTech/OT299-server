package com.alkemy.ong.domain.categories;

import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
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

    public void deleteById(Long id) {
        categoriesGateway.deleteById(id);
    }
}