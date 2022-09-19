package com.alkemy.ong.web;

import com.alkemy.ong.domain.activities.ActivityService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/activities")
@AllArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody ActivityDto activityDto) {
        activityService.createActivity(activityDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
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
