package com.alkemy.ong.web.controllers;

import com.alkemy.ong.data.entities.CategoriesEntity;
import com.alkemy.ong.data.repositories.CategoriesRepository;
import com.alkemy.ong.domain.OngPage;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import com.alkemy.ong.web.CategoriesController;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoriesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CategoriesRepository categoriesRepository;

    @Autowired
    ObjectMapper objectMapper;

    private String url = "http://localhost:8080/categories";

    @Test
    void pageAllSuccess() throws Exception {
        OngPage<CategoriesEntity> ongPage = createOngPage();

        when(categoriesRepository.findAll(PageRequest.of(0, 10))).thenReturn(new PageImpl<>(ongPage.getBody()));
        mockMvc.perform(MockMvcRequestBuilders.get(url+"?page=0")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ongPage)))
                .andExpect(jsonPath("body").isArray())
                .andExpect(jsonPath("$.body",hasSize(4)))
                .andExpect(jsonPath("$.body.[0].id", is(1)))
                .andExpect(jsonPath("$.body.[1].id", is(2)))
                .andExpect(jsonPath("$.body.[2].id", is(3)))
                .andExpect(jsonPath("$.body.[3].id", is(4)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getByIdSuccess() throws Exception{

        CategoriesEntity categoriesEntity = createCategoriesEntity(1L, "Random Name", "Random Description", "Random Image.jpg");
        CategoriesController.CategoriesDto categoriesDto = createCategoriesDto(1L, "Random Name", "Random Description", "Random Image.jpg");

        when(categoriesRepository.findById(1L)).thenReturn(Optional.of(categoriesEntity));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/categories/{id}", 1)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @WithMockUser(roles = "1")
    void createSuccess() throws Exception {
        CategoriesEntity categoriesEntity = createCategoriesEntity(1L,"test_name","test.png","description");
        CategoriesEntity categoriesEntitySaved = createCategoriesEntity(1L, "test_name","test.png","description");
        CategoriesController.CategoriesDto categoriesDto = createCategoriesDto(1L,"test_name","test.png","description");
        when(categoriesRepository.save(categoriesEntity)).thenReturn(categoriesEntitySaved);
        mockMvc.perform(post(url+"/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoriesDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("test_name")))
                .andExpect(jsonPath("$.image", is("test.png")))
                .andExpect(jsonPath("$.description", is("description")));
    }

    @Test
    @WithMockUser(roles = "1")
    void createFailed() throws Exception {
        CategoriesController.CategoriesDto categoriesDto = createCategoriesDto(null, "test_name", null, null);
        mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(APPLICATION_JSON)
                .content(String.valueOf(categoriesDto))).andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "2")
    void createUnauthorizedUser() throws Exception {
        mockMvc.perform(post(url)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "1")
    void updateSuccess() throws Exception {
        CategoriesEntity categories = createCategoriesEntity(1L, "test_name1","test1.png","description_1");
        CategoriesController.CategoriesDto categoriesDto = createCategoriesDto(1L, "test_name_update","update.png","description_update");
        when(categoriesRepository.findById(1L)).thenReturn(Optional.of(categories));
        when(categoriesRepository.save(categories)).thenReturn(categories);
        mockMvc.perform(put(url+"/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoriesDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("test_name_update")))
                .andExpect(jsonPath("$.image", is("update.png")))
                .andExpect(jsonPath("$.description", is("description_update")));
    }

    @Test
    @WithMockUser(roles = "1")
    void updateNotFound() throws Exception {
        when(categoriesRepository.findById(32L)).thenThrow(new ResourceNotFoundException("Categories", "id", 32L));
        CategoriesController.CategoriesDto categoriesDto = createCategoriesDto(32L, "test_name","test.png","description_example");
        mockMvc.perform(put(url+"/32")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoriesDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "2")
    void updateUnauthorizedUser() throws Exception {
        mockMvc.perform(put(url+"/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "1")
    void deleteSuccess() throws Exception {
        CategoriesEntity categories = createCategoriesEntity(1L, "name01", "test01.png", "description01");
        when(categoriesRepository.findById(1L)).thenReturn(Optional.of(categories));
        doNothing().when(categoriesRepository).deleteById(1L);

        mockMvc.perform(delete(url+"/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteUnauthorized() throws Exception {
        mockMvc.perform(delete(url+"/1")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "2")
    void deleteUnauthorizedUser() throws Exception {
        mockMvc.perform(delete(url+"/1")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "1")
    void deleteNotFound() throws Exception{
        when(categoriesRepository.findById(74L)).thenThrow(new ResourceNotFoundException("Categories", "id", 74L));

        mockMvc.perform(delete(url+"/3"))
                .andExpect(status().isNotFound());
    }

    private CategoriesEntity createCategoriesEntity(Long id, String name, String image, String description) {
        return CategoriesEntity.builder().id(id).name(name).image(image).description(description).build();
    }

    private CategoriesController.CategoriesDto createCategoriesDto(Long id, String name, String image, String description) {
        return CategoriesController.CategoriesDto.builder().id(id).name(name).image(image).description(description).build();
    }

    private OngPage createOngPage() {

        OngPage ongPage = new OngPage<>();
        ongPage.setBody(Arrays.asList(
                createCategoriesEntity(1L, "test_name1","test1.png","description_1"),
                createCategoriesEntity(2L, "test_name2","test2.png","description_2"),
                createCategoriesEntity(3L, "test_name3","test3.png","description_3"),
                createCategoriesEntity(4L, "test_name4","test4.png","description_4")
        ));
        ongPage.setNextPage("/categories?page=1");
        ongPage.setPreviousPage("null");
        return ongPage;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CategoriesDto {
        private Long id;
        @NotBlank(message = "Name is required")
        @Pattern(regexp="^[A-Za-z]*$", message = "Invalid Input")
        private String name;
        private String description;
        private String image;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private boolean deleted = false;
    }

}
