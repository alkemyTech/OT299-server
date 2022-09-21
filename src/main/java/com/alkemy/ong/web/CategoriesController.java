package com.alkemy.ong.web;

import com.alkemy.ong.domain.categories.Categories;
import com.alkemy.ong.domain.categories.CategoriesService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/categories")
@RestController
public class CategoriesController {

    CategoriesService categoriesService;

    @GetMapping("")
    public ResponseEntity<CategoriesDtoByName> findAll() {
        return new  ResponseEntity(categoriesService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
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
