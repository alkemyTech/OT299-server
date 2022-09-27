package com.alkemy.ong.web;

import com.alkemy.ong.domain.news.New;
import com.alkemy.ong.domain.news.NewService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/news")
@AllArgsConstructor
public class NewController {

    NewService newService;

    @DeleteMapping (path = "/{id}")
    public ResponseEntity delete(@PathVariable (value = "id") Long id){
    newService.deleteById(id);
    return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public List<NewDto> findAll(){
        return newService.findAll()
                .stream()
                .map(this::toDto)
                .collect(toList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<New> findById (@PathVariable Long id){
        return ResponseEntity.ok(newService.findById(id));
    }

    private NewDto toDto (New news){
        return NewDto.builder()
                .id(news.getId())
                .name(news.getName())
                .content(news.getContent())
                .image(news.getImage())
                .categoryId(news.getCategoryId())
                .createdAt(news.getCreatedAt())
                .updatedAt(news.getUpdatedAt())
                .deleted(news.isDeleted())
                .build();
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class NewDto {
        private Long id;
        private String name;
        private String content;
        private String image;
        private Long categoryId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private boolean deleted;

    }
}
