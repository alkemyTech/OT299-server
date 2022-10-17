package com.alkemy.ong.web;

import com.alkemy.ong.data.entities.TestimonialEntity;
import com.alkemy.ong.data.repositories.TestimonialRepository;
import com.alkemy.ong.domain.OngPage;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

//import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TestimonialControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    private String url = "http://localhost:8080/testimonials";

    @MockBean
    TestimonialRepository testimonialRepository;

    @Test
    void pageAll() throws Exception {
        OngPage<TestimonialEntity> ongPage = createOngPage();

        when(testimonialRepository.findAll(PageRequest.of(0, 10))).thenReturn(new PageImpl<>(ongPage.getBody()));
        mockMvc.perform(MockMvcRequestBuilders.get(url+"?page=0")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ongPage)))
                .andExpect(jsonPath("body").isArray())
                .andExpect(jsonPath("$.body",hasSize(4)))
                //.andExpect()
                .andExpect(jsonPath("$.body.[0].id", is(1)))
                .andExpect(jsonPath("$.body.[1].id", is(2)))
                .andExpect(jsonPath("$.body.[2].id", is(3)))
                .andExpect(jsonPath("$.body.[3].id", is(4)))
                .andExpect(status().isOk());
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
    void deleteSuccess() throws Exception {
        TestimonialEntity testimonial = createTestimonialEntity(1L, "Walter White", "www.image.com", "Content example");
        when(testimonialRepository.findById(1L)).thenReturn(Optional.of(testimonial));
        doNothing().when(testimonialRepository).deleteById(1L);

        mockMvc.perform(delete(url+"/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles = "1")
    void createFailed() throws Exception {
        TestimonialDto testimonialDto = createTestimonialDto(null, "test_name", null, null);
        mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(APPLICATION_JSON)
                .content(String.valueOf(testimonialDto))).andExpect(status().isBadRequest());
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
    void createSuccess() throws Exception {
        TestimonialEntity testimonialEntity = createTestimonialEntity(null,"test_name","test.png","content_example");
        TestimonialEntity testimonialEntitySaved = createTestimonialEntity(1L, "test_name","test.png","content_example");
        TestimonialDto testimonialDto = createTestimonialDto(null,"test_name","test.png","content_example");
        when(testimonialRepository.save(testimonialEntity)).thenReturn(testimonialEntitySaved);
        mockMvc.perform(post(url)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testimonialDto)))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("test_name")))
                .andExpect(jsonPath("$.image", is("test.png")))
                .andExpect(jsonPath("$.content", is("content_example")));
    }

    @Test
    @WithMockUser(roles = "1")
    void updateNotFound() throws Exception {
        when(testimonialRepository.findById(32L)).thenThrow(new ResourceNotFoundException("Testimonial", "id", 32L));
        TestimonialDto testimonialDto = createTestimonialDto(32L, "test_name","test.png","content_example");
        mockMvc.perform(put(url+"/32")
                .contentType(APPLICATION_JSON)
                .content(String.valueOf(testimonialDto)))
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
    void updateSuccess() throws Exception {
        TestimonialEntity testimonial = createTestimonialEntity(1L, "test_name1","test1.png","content_1");
        TestimonialDto testimonialDto = createTestimonialDto(1L, "test_name1","test1.png","content_1");
        when(testimonialRepository.findById(1L)).thenReturn(Optional.of(testimonial));
        when(testimonialRepository.save(testimonial)).thenReturn(testimonial);
        mockMvc.perform(put(url+"/1")
                .contentType(APPLICATION_JSON)
                .content(String.valueOf(testimonialDto)))
                .andExpect(status().isOk());
    }

    private TestimonialEntity createTestimonialEntity(Long id, String name, String image, String content) {
        return TestimonialEntity.builder().id(id).name(name).image(image).content(content).build();
    }

    private TestimonialDto createTestimonialDto(Long id, String name, String image, String content) {
        return TestimonialDto.builder().id(id).name(name).image(image).content(content).build();
    }


    private OngPage createOngPage() {
        OngPage ongPage = new OngPage<>();
        ongPage.setBody(Arrays.asList(
                createTestimonialEntity(1L, "test_name1","test1.png","content_1"),
                createTestimonialEntity(2L, "test_name2","test2.png","content_2"),
                createTestimonialEntity(3L, "test_name3","test3.png","content_3"),
                createTestimonialEntity(4L, "test_name4","test4.png","content_4")
        ));
        ongPage.setNextPage(url+"?page=1");
        ongPage.setPreviousPage("null");
        return ongPage;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TestimonialDto {
        private Long id;
        @NotBlank(message = "Name is required")
        private String name;
        @NotBlank(message = "Content is required")
        private String content;
        @NotBlank(message = "Image is required")
        private String image;
        private LocalDateTime updatedAt;
        private LocalDateTime createdAt;
        private boolean deleted = false;
    }


}