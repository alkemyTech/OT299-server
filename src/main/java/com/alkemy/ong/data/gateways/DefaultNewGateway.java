package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.CategoriesEntity;
import com.alkemy.ong.data.entities.NewEntity;
import com.alkemy.ong.data.repositories.CategoriesRepository;
import com.alkemy.ong.data.repositories.NewRepository;
import com.alkemy.ong.domain.OngPage;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import com.alkemy.ong.domain.news.New;
import com.alkemy.ong.domain.news.NewGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;



import static java.util.stream.Collectors.*;

@Component
@RequiredArgsConstructor
public class DefaultNewGateway implements NewGateway {

    private final NewRepository newRepository;
    private final CategoriesRepository categoriesRepository;

    @Override
    public void deleteById(Long id) {
        NewEntity news = newRepository.findById(id).orElseThrow(() ->
            new ResourceNotFoundException("New", "id", id));
        newRepository.deleteById(news.getId());
    }

   @Override
    public OngPage<New> findAll(int page) {
       Page <NewEntity> newsEntityPage = newRepository.findAll(PageRequest.of(page, 10));
       List <New> news = newsEntityPage.getContent().stream().map(this::toModel).collect(toList());
       OngPage <New> newsPage = new OngPage<>();
       newsPage.setBody(news);
       newsPage.setPreviousPage(page);
       newsPage.setNextPage(page, newsEntityPage.getTotalPages());
       newsPage.setResource("news");
       return newsPage;
    }



    @Override
    public New findById(Long id) {
        NewEntity newEntity = newRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("New","id", id));
        return toModel(newEntity);
    }

    @Override
    public New save(New news) {
        CategoriesEntity categoriesEntity = categoriesRepository.findById(news.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("Category", "id", news.getCategoryId()));

        return toModel((newRepository.save(toEntity(news, categoriesEntity))));
    }
    @Override
    public New update(New news, Long id) {

        NewEntity newEntity = newRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("New", "id", id));

        CategoriesEntity categoryEntity = categoriesRepository.findById(news.getCategoryId()).orElseThrow(()->
                new ResourceNotFoundException("Category", "id", id));


        return toModel(updateNew(newEntity, news, categoryEntity));
    }

    private New toModel(NewEntity newEntity){
        return New.builder()
                .id(newEntity.getId())
                .name(newEntity.getName())
                .content(newEntity.getContent())
                .image(newEntity.getImage())
                .categoryId(newEntity.getCategoryId().getId())
                .createdAt(newEntity.getCreatedAt())
                .updatedAt(newEntity.getUpdatedAt())
                .deleted(newEntity.isDeleted())
                .build();
    }
    public NewEntity toEntity (New news, CategoriesEntity categories){
        return NewEntity.builder()
                .name(news.getName())
                .content(news.getContent())
                .image(news.getImage())
                .categoryId(categories)
                .createdAt(news.getCreatedAt())
                .updatedAt(news.getUpdatedAt())
                .deleted(news.isDeleted())
                .build();
    }
    private NewEntity updateNew (NewEntity newEntity, New news, CategoriesEntity categoryEntity){
        newEntity.setName(news.getName());
        newEntity.setContent(news.getContent());
        newEntity.setImage(news.getImage());
        newEntity.setCategoryId(categoryEntity);
        return newRepository.save(newEntity);
    }
}
