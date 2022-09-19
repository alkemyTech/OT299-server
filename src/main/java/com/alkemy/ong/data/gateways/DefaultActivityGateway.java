package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.ActivityEntity;
import com.alkemy.ong.data.repositories.ActivityRepository;
import com.alkemy.ong.domain.activities.Activity;
import com.alkemy.ong.domain.activities.ActivityGateway;
import com.alkemy.ong.web.ActivityController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultActivityGateway implements ActivityGateway {

    private final ActivityRepository repository;

    @Override
    public void createActivity(ActivityController.ActivityDto activityDto) {
        repository.save(toentity(activityDto));
    }

    private Activity tomodel(ActivityEntity entity) {
        // Convertir a modelo
        return new Activity(
                entity.getId(),
                entity.getName(),
                entity.getContent(),
                entity.getImage(),
                entity.getUpdatedAt(),
                entity.getCreatedAt(),
                entity.isDeleted()
        );
    }

    private ActivityEntity toentity(ActivityController.ActivityDto activityDto){
        return new ActivityEntity(
                activityDto.getId(),
                activityDto.getName(),
                activityDto.getContent(),
                activityDto.getImage(),
                activityDto.getUpdatedAt(),
                activityDto.getCreatedAt(),
                activityDto.isDeleted()
        );
    }

}
