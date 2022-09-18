package com.alkemy.ong.web;

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
        return new ContactsDto(
                contacts.getId(),
                contacts.getName(),
                contacts.getPhone(),
                contacts.getEmail(),
                contacts.getMessage(),
                contacts.getCreatedAt(),
                contacts.getUpdatedAt(),
                contacts.isDeleted()
        );
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
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

