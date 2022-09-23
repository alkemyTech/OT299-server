package com.alkemy.ong.web;

import com.alkemy.ong.domain.categories.Categories;
import com.alkemy.ong.domain.categories.CategoriesService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@AllArgsConstructor
@RequestMapping("/categories")
@RestController
public class CategoriesController {

    CategoriesService categoriesService;

    @GetMapping("")
    public ResponseEntity<CategoriesDtoByName> findAll() {
        return new ResponseEntity(categoriesService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categories> findCategoryById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(categoriesService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<CategoriesDto> createCategory(@Valid @RequestBody CategoriesDto categoriesDto){
        Categories categories = categoriesService.createCategory(toModel(categoriesDto));
        return new ResponseEntity(toDto(categories), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private Categories toModel(CategoriesDto categoriesDto){
        return Categories.builder()
                .name(categoriesDto.name)
                .description(categoriesDto.description)
                .image(categoriesDto.image)
                .createdAt(categoriesDto.createdAt)
                .updatedAt(categoriesDto.updatedAt)
                .deleted(categoriesDto.deleted)
                .build();
    }

    private CategoriesDto toDto(Categories categories) {
        return CategoriesDto.builder()
                .id(categories.getId())
                .name(categories.getName())
                .description(categories.getDescription())
                .image(categories.getImage())
                .createdAt(categories.getCreatedAt())
                .updatedAt(categories.getUpdatedAt())
                .deleted(categories.isDeleted())
                .build();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CategoriesDto {
        private Long id;
        @NotBlank(message = "Name is required")
        @Pattern(regexp="^[A-Za-z]*$", message = "Invalid Input")
        private String name;
        private String description;
        private String image;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private boolean deleted = false;
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
