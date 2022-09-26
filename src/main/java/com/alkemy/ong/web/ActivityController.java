package com.alkemy.ong.web;

import com.alkemy.ong.domain.activities.Activity;
import com.alkemy.ong.domain.activities.ActivityService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/activities")
@AllArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityDto> create(@RequestBody ActivityDto activityDto) {
        Activity activity = activityService.createActivity(toModel(activityDto));
        return new ResponseEntity<>(toDto(activity), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ActivityDto> update(@RequestParam(name = "id") Long id, @Valid @RequestBody ActivityDto activityDto) {
        Activity activity = activityService.update(id, toModel(activityDto));
        return new ResponseEntity<>(toDto(activity), HttpStatus.OK);
    }


    // Dto -> Model
    public Activity toModel(ActivityDto activityDto){
        return Activity.builder().id(activityDto.id).name(activityDto.name)
                .content(activityDto.content).image(activityDto.image)
                .updatedAt(activityDto.updatedAt).createdAt(activityDto.createdAt)
                .deleted(activityDto.deleted).build();

    }

    // Model -> Dto
    public ActivityDto toDto(Activity activity){
        return ActivityDto.builder().id(activity.getId()).name(activity.getName())
                .content(activity.getContent()).image(activity.getImage())
                .updatedAt(activity.getUpdatedAt()).createdAt(activity.getCreatedAt())
                .deleted(activity.isDeleted()).build();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ActivityDto {
        private Long id;
        private String name;
        private String content;
        private String image;
        private LocalDateTime updatedAt;
        private LocalDateTime createdAt;
        private boolean deleted = false;
    }


}
