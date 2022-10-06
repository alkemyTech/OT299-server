package com.alkemy.ong.web;

import com.alkemy.ong.domain.contacts.Contacts;
import com.alkemy.ong.domain.contacts.ContactsService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;
import static java.util.stream.Collectors.*;

@AllArgsConstructor
@RestController
@RequestMapping("/contacts")
public class ContactsController {

    ContactsService contactsService;

    @PreAuthorize("permitAll()")
    @GetMapping
    public List<ContactsDto> findAll(){
        return contactsService.findAll().stream().map(this::toDto).collect(toList());
    }

    @PreAuthorize("hasRole('ROLE_1')")
    @PostMapping
    public ResponseEntity<ContactsController.ContactsDto> createContact(@Valid @RequestBody ContactsController.ContactsDto contactsDto){
        Contacts contacts = contactsService.createContact(toModel(contactsDto));
        return new ResponseEntity(toDto(contacts), HttpStatus.CREATED);
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

    private Contacts toModel(ContactsController.ContactsDto contactsDto){
        return Contacts.builder()
                .name(contactsDto.name)
                .phone(contactsDto.phone)
                .email(contactsDto.email)
                .message(contactsDto.message)
                .createdAt(contactsDto.createdAt)
                .updatedAt(contactsDto.updatedAt)
                .deleted(contactsDto.deleted)
                .build();
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




