package com.alkemy.ong.web;

import com.alkemy.ong.domain.OngPage;
import com.alkemy.ong.domain.categories.CategoriesService;
import com.alkemy.ong.domain.news.New;
import com.alkemy.ong.domain.news.NewService;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;




@RestController
@RequestMapping("/news")
@AllArgsConstructor
public class NewController {

    NewService newService;

    CategoriesService categoriesService;

    @DeleteMapping (path = "/{id}")
    public ResponseEntity delete(@PathVariable (value = "id") Long id){
    newService.deleteById(id);
    return ResponseEntity.noContent().build();
    }

  /* @GetMapping("")
    public List<NewDto> findAll(){

        return newService.findAll()
                .stream()
                .map(this::toDto)
                .collect(toList());
    }*/
  @GetMapping("")
    public ResponseEntity<?> findAll (@RequestParam Map<String, Object> params) {
       int page = Integer.valueOf((String) params.get("page")) ;

       OngPage<New> pageNew = newService.findAll(page);
       /*int totalPage = pageNew.getTotalPages();
       if (totalPage > 0) {
           List<NewDto> pages = newService.findAll()
                   .stream()
                   .map(this::toDto)
                   .collect(toList());
       }*/

       return ResponseEntity.ok(pageNew);
   }

    @GetMapping(path = "/{id}")
    public ResponseEntity<New> findById (@PathVariable Long id){
        return ResponseEntity.ok(newService.findById(id));
    }
    @PostMapping
    public ResponseEntity<NewDto> create(@Valid @RequestBody NewDto newDto){
         New news = newService.create(toModel(newDto));
         return new ResponseEntity<>(toDto(news), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<NewController.NewDto>update (@Valid @RequestBody NewController.NewDto newDto,
                                                             @PathVariable final Long id){
        New news = newService.update(toModel(newDto), id);
        return new ResponseEntity<>(toDto(news), HttpStatus.OK );
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

    private New toModel (NewDto newDto){
        return New.builder()
                .id(newDto.getId())
                .name(newDto.getName())
                .content(newDto.getContent())
                .image(newDto.getImage())
                .categoryId(newDto.getCategoryId())
                .createdAt(newDto.getCreatedAt())
                .updatedAt(newDto.getUpdatedAt())
                .deleted(newDto.isDeleted())
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
