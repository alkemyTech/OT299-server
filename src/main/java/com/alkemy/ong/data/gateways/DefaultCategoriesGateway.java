package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.CategoriesEntity;
import com.alkemy.ong.data.repositories.CategoriesRepository;
import com.alkemy.ong.domain.OngPage;
import com.alkemy.ong.domain.categories.Categories;
import com.alkemy.ong.domain.categories.CategoriesGateway;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import java.util.List;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@Component
public class DefaultCategoriesGateway implements CategoriesGateway {

    private final CategoriesRepository categoriesRepository;

    @Value("${pagination.pageSize}")
    private int PAGE_SIZE;

    @Override
    public OngPage<Categories> findAll(int pageNumber) {
        Page<CategoriesEntity> categoriesEntityPage = categoriesRepository.findAll(PageRequest.of(pageNumber, PAGE_SIZE));
        List<Categories> categories = categoriesEntityPage.getContent().stream()
                .map(this::toModel)
                .collect(toList());

        return new OngPage.OngPageBuilder<Categories>("categories")
                .body(categories)
                .pageNumber(pageNumber)
                .totalPages(categoriesEntityPage.getTotalPages())
                .build();
    }

    @Override
    public Categories findById(long id){
        CategoriesEntity categoriesEntity = categoriesRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Category", "id", id));
        return toModel(categoriesEntity);
    }

    @Override
    public Categories createCategory(Categories categories) {
        return toModel(categoriesRepository.save(toEntity(categories)));
    }

    @Override
    public Categories updateCategory(Long id, Categories categories) {
        CategoriesEntity categoriesEntity = categoriesRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Category", "id", id));
        return toModel(updateEntity(categoriesEntity, categories));
    }

    @Override
    public void deleteById(Long id) {
        Categories categories = toModel(categoriesRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Category", "id", id)));
        categoriesRepository.deleteById(categories.getId());
    }

    private Categories toModel (CategoriesEntity categoriesEntity){
        return Categories.builder()
                .id(categoriesEntity.getId())
                .name(categoriesEntity.getName())
                .description(categoriesEntity.getDescription())
                .image(categoriesEntity.getImage())
                .createdAt(categoriesEntity.getCreatedAt())
                .updatedAt(categoriesEntity.getUpdatedAt())
                .deleted(categoriesEntity.isDeleted())
                .build();
    }

    private CategoriesEntity toEntity(Categories categories) {
        return CategoriesEntity.builder()
                .id(categories.getId())
                .name(categories.getName())
                .description(categories.getDescription())
                .image(categories.getImage())
                .createdAt(categories.getCreatedAt())
                .updatedAt(categories.getUpdatedAt())
                .deleted(categories.isDeleted())
                .build();
    }

    private CategoriesEntity updateEntity(CategoriesEntity categoriesEntity, Categories categories) {
        categoriesEntity.setName(categories.getName());
        categoriesEntity.setDescription(categories.getDescription());
        categoriesEntity.setImage(categories.getImage());
        return categoriesRepository.save(categoriesEntity);
    }

}
