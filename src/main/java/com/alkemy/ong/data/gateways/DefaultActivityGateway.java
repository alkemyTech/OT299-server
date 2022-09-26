package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.ActivityEntity;
import com.alkemy.ong.data.repositories.ActivityRepository;
import com.alkemy.ong.domain.activities.Activity;
import com.alkemy.ong.domain.activities.ActivityGateway;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultActivityGateway implements ActivityGateway {

    private final ActivityRepository repository;

    @Override
    public Activity createActivity(Activity activity) {
        return toModel(repository.save(toEntity(activity)));
    }

    @Override
    public Activity update(Long id, Activity activity) {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Activity", "id", id));
        activity.setId(id);
        return toModel(repository.save(toEntityUpdate(activity)));
    }

    private Activity toModel(ActivityEntity entity) {
        return Activity.builder().id(entity.getId()).name(entity.getName())
                .content(entity.getContent()).image(entity.getImage())
                .updatedAt(entity.getUpdatedAt()).createdAt(entity.getCreatedAt())
                .deleted(entity.isDeleted()).build();
    }

    private ActivityEntity toEntity(Activity activity){
       return ActivityEntity.builder().id(activity.getId()).name(activity.getName())
               .content(activity.getContent()).image(activity.getImage())
               .updatedAt(activity.getUpdatedAt()).createdAt(activity.getCreatedAt())
               .deleted(activity.isDeleted()).build();
    }

    private ActivityEntity toEntityUpdate(Activity activity) {
        return ActivityEntity.builder().id(activity.getId()).name(activity.getName())
                .content(activity.getContent()).image(activity.getImage()).build();
    }

}
