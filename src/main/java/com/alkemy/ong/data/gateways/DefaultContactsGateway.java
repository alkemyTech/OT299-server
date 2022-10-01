package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.ContactsEntity;
import com.alkemy.ong.data.repositories.ContactsRepository;
import com.alkemy.ong.domain.contacts.Contacts;
import com.alkemy.ong.domain.contacts.ContactsGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@Component
public class DefaultContactsGateway implements ContactsGateway {

    private final ContactsRepository contactsRepository;

    @Override
    public List<Contacts> findAll(){
        return contactsRepository.findAll().stream().map(this::toModel).collect(toList());
    }

    @Override
    public Contacts createContact(Contacts contacts) {
        return toModel(contactsRepository.save(toEntity(contacts)));
    }

    private Contacts toModel (ContactsEntity contactsEntity) {
        return Contacts.builder()
                .id(contactsEntity.getId())
                .name(contactsEntity.getName())
                .phone(contactsEntity.getPhone())
                .message(contactsEntity.getMessage())
                .email(contactsEntity.getEmail())
                .createdAt(contactsEntity.getCreatedAt())
                .updatedAt(contactsEntity.getUpdatedAt())
                .deleted(contactsEntity.isDeleted())
                .build();
    }

    private ContactsEntity toEntity(Contacts contacts) {
        return ContactsEntity.builder()
                .id(contacts.getId())
                .name(contacts.getName())
                .phone(contacts.getPhone())
                .email(contacts.getEmail())
                .createdAt(contacts.getCreatedAt())
                .updatedAt(contacts.getUpdatedAt())
                .deleted(contacts.isDeleted())
                .build();
    }

}
