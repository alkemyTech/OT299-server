package com.alkemy.ong.web;

import com.alkemy.ong.domain.categories.Categories;
import com.alkemy.ong.domain.categories.CategoriesService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import static java.util.stream.Collectors.*;

@AllArgsConstructor
@RequestMapping("/categories")
@RestController
public class CategoriesController {

    CategoriesService categoriesService;

    @GetMapping("")
    public List<CategoriesDtoByName> findAll() {
        return categoriesService.findAll().stream().map(this::toDtoByName).collect(toList());
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoriesService.deleteById(id);
    }

    private CategoriesDtoByName toDtoByName(Categories categories) {
        return CategoriesDtoByName.builder().name(categories.getName()).build();
    }

    @Getter
    @Setter
    @Builder
    public static class CategoriesDtoByName {
        private String name;
    }


}
