package com.alkemy.ong.web;

import com.alkemy.ong.domain.categories.Categories;
import com.alkemy.ong.domain.categories.CategoriesService;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static java.util.stream.Collectors.*;

@AllArgsConstructor
@RestController
public class CategoriesController {

    CategoriesService categoriesService;

    @GetMapping("/categories")
    public List<CategoriesDtoByName> findAll() {
        return categoriesService.findAll().stream().map(this::toDtoByName).collect(toList());
    }


    private CategoriesDtoByName toDtoByName (Categories categories) {
        return CategoriesDtoByName.builder().name(categories.getName()).build();
    }
    @Getter
    @Setter
    @Builder
    public static class CategoriesDtoByName {
        private String name;
    }
}
