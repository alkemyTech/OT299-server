package com.alkemy.ong.domain.categories;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface CategoriesGateway {
    List<Categories> findAll();
}
