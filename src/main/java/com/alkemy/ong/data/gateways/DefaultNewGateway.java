package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.NewEntity;
import com.alkemy.ong.data.repositories.NewRepository;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import com.alkemy.ong.domain.news.New;
import com.alkemy.ong.domain.news.NewGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultNewGateway implements NewGateway {

    private final NewRepository newRepository;
    @Override
    public void deleteById(Long id) {
        NewEntity news = newRepository.findById(id).orElseThrow(() ->
            new ResourceNotFoundException("New", "id", id));
        newRepository.deleteById(news.getId());
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
}
