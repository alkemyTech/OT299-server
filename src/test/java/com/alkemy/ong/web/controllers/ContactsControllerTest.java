package com.alkemy.ong.web.controllers;

import com.alkemy.ong.data.entities.ContactsEntity;
import com.alkemy.ong.data.repositories.ContactsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ContactsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ContactsRepository contactsRepository;

    @Autowired
    ObjectMapper objectMapper;

    private String url = "http://localhost:8080/contacts";

    @Test
    void findAllSuccess() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get(url)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
        }

    @Test
    @WithMockUser(roles = "1")
    void createSuccess() throws Exception {
        ContactsEntity contactsEntity = createContactsEntity(1L,"name", 4444444L,"email","message");
        ContactsEntity contactsEntitySaved = createContactsEntity(1L,"name", 4444444L,"email","message");
        ContactsDto contactsDto = createContactsDto(1L,"name", 4444444L,"email","message");
        when(contactsRepository.save(contactsEntity)).thenReturn(contactsEntitySaved);
        mockMvc.perform(post(url)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contactsDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.phone", is(4444444)))
                .andExpect(jsonPath("$.email", is("email")))
                .andExpect(jsonPath("$.message", is("message")));
    }

    @Test
    @WithMockUser(roles = "1")
    void createFailed() throws Exception {
        ContactsDto contactsDto = createContactsDto(1L,"name", 4444444L,"email","message");
        mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(APPLICATION_JSON)
                .content(String.valueOf(contactsDto))).andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "2")
    void createUnauthorizedUser() throws Exception {
        mockMvc.perform(post(url)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    private ContactsEntity createContactsEntity(Long id, String name, Long phone, String email, String message) {
        return ContactsEntity.builder().id(id).name(name).phone(phone).email(email).message(message).build();
    }

    private ContactsDto createContactsDto(Long id, String name, Long phone, String email, String message) {
        return ContactsDto.builder().id(id).name(name).phone(phone).email(email).message(message).build();
    }

    @Getter
    @Setter
    @Builder
    public static class ContactsDto{
        private long id;
        @NotBlank(message = "Name is required")
        @Pattern(regexp="^[A-Za-z]*$", message = "Invalid Input")
        private String name;
        private long phone;
        @NotBlank(message = "email is required")
        @Pattern(regexp="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Invalid Input")
        private String email;
        private String message;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private boolean deleted = false;
    }

}
