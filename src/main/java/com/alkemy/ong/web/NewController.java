package com.alkemy.ong.web;

import com.alkemy.ong.domain.OngPage;
import com.alkemy.ong.domain.categories.CategoriesService;
import com.alkemy.ong.domain.comments.CommentService;
import com.alkemy.ong.domain.news.New;
import com.alkemy.ong.domain.news.NewService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;


@RestController
@RequestMapping("/news")
@AllArgsConstructor
public class NewController {

    NewService newService;

    CategoriesService categoriesService;

    CommentController commentController;

    private final CommentService commentService;

    @DeleteMapping (path = "/{id}")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(
            mediaType = "application/json", examples = {@ExampleObject(name= "errors",
            value = "{errors: [Invalid Input]}")})})
    @ApiResponse(responseCode = "404", description = "Not Found",content = {@Content(
            mediaType = "application/json", examples = {@ExampleObject(name= "errors",
            value = "error: Category not found with: id :")})})
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(
            mediaType = "application/json", examples = {@ExampleObject(name= "errors",
            value = "{error: [Internal Server Error]}")})})
    public ResponseEntity delete(@PathVariable (value = "id") Long id){
        newService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
  @GetMapping
  @ApiResponse(responseCode = "200", description = "OK")
  @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(
          mediaType = "application/json", examples = {@ExampleObject(name= "errors",
          value = "{error: [Internal Server Error]}")})})
    public ResponseEntity<OngPage<New>> findAll (@RequestParam Integer page) {

       OngPage<New> pageNews = newService.findAll(page);

       return ResponseEntity.ok(pageNews);
    }

    @GetMapping("{id}/comments")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(
            mediaType = "application/json", examples = {@ExampleObject(name= "errors",
            value = "{errors: [Invalid Input]}")})})
    @ApiResponse(responseCode = "404",description = "Not Found", content = {@Content(
            mediaType = "application/json", examples = {@ExampleObject(name= "errors",
            value = "error: Category not found with: id :")})})
    @ApiResponse(responseCode = "500",description = "Internal Server Error", content = {@Content(
            mediaType = "application/json", examples = {@ExampleObject(name= "errors",
            value = "{error: [Internal Server Error]}")})})
    public ResponseEntity<NewDto> findCommentsByNewId(@PathVariable Long id){
        NewDto newDto = toDto(newService.findById(id));
        List<CommentController.CommentCreateDto> commentCreateDto = commentService.findByNewsId(id)
                .stream()
                .map(commentController::toDtoForCreate)
                .collect(toList());
        return ResponseEntity.ok()
                .body(addCommentsToDto(newDto, commentCreateDto));
    }

    @GetMapping(path = "/{id}")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(
            mediaType = "application/json", examples = {@ExampleObject(name= "errors",
            value = "{errors: [Invalid Input]}")})})
    @ApiResponse(responseCode = "404",description = "Not Found", content = {@Content(
            mediaType = "application/json", examples = {@ExampleObject(name= "errors",
            value = "error: Category not found with: id :")})})
    @ApiResponse(responseCode = "500",description = "Internal Server Error", content = {@Content(
            mediaType = "application/json", examples = {@ExampleObject(name= "errors",
            value = "{error: [Internal Server Error]}")})})
    public ResponseEntity<New> findById (@PathVariable Long id){

        return ResponseEntity.ok(newService.findById(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "400",  description = "Bad Request", content = {@Content(
            mediaType = "application/json", examples = {@ExampleObject(name= "errors",
            value = "{errors: [Name is required]}")})})
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(
            mediaType = "application/json", examples = {@ExampleObject(name= "errors",
            value = "{error: [Internal Server Error]}")})})
    public ResponseEntity<NewDto> create(@Valid @RequestBody NewDto newDto){
         New news = newService.create(toModel(newDto));
         return new ResponseEntity<>(toDto(news), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400",  description = "Bad Request", content = {@Content(
            mediaType = "application/json", examples = {@ExampleObject(name= "errors",
            value = "{errors: [Invalid Input]}")})})
    @ApiResponse(responseCode = "404", description = "Not Found", content = {@Content(
            mediaType = "application/json", examples = {@ExampleObject(name= "errors",
            value = "error: Category not found with: id :")})})
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(
            mediaType = "application/json", examples = {@ExampleObject(name= "errors",
            value = "{error: [Internal Server Error]}")})})
    public ResponseEntity<NewController.NewDto>update (@Valid @RequestBody NewController.NewDto newDto,
                                                             @PathVariable final Long id){
        New news = newService.update(toModel(newDto), id);
        return new ResponseEntity<>(toDto(news), HttpStatus.OK );
    }

    private NewDto addCommentsToDto(NewDto newDto, List<CommentController.CommentCreateDto> commentCreateDtos){
        newDto.setComments(commentCreateDtos);
        return newDto;

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
        private List<CommentController.CommentCreateDto> comments;

    }
}
