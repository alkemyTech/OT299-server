package com.alkemy.ong.web;

import com.alkemy.ong.data.entities.ContactsEntity;
import com.alkemy.ong.domain.contacts.Contacts;
import com.alkemy.ong.domain.contacts.ContactsService;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.List;
import static java.util.stream.Collectors.*;

@AllArgsConstructor
@RestController
public class ContactsController {

    ContactsService contactsService;

    @GetMapping("/contacts")
    public List<ContactsDto> findAll(){
        return contactsService.findAll().stream().map(this::toDto).collect(toList());
    }

    private ContactsDto toDto (Contacts contacts){
        return ContactsDto.builder()
                .id(contacts.getId())
                .name(contacts.getName())
                .phone(contacts.getPhone())
                .email(contacts.getEmail())
                .message(contacts.getMessage())
                .createdAt(contacts.getCreatedAt())
                .updatedAt(contacts.getUpdatedAt())
                .deleted(contacts.isDeleted())
                .build();
        }

    @Getter
    @Setter
    @Builder
    public static class ContactsDto{
        private long id;
        private String name;
        private long phone;
        private String email;
        private String message;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private boolean deleted = false;
    }

}




