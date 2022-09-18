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

    private Contacts toModel (ContactsEntity contactsEntity) {
        return new Contacts(
                contactsEntity.getId(),
                contactsEntity.getName(),
                contactsEntity.getPhone(),
                contactsEntity.getMessage(),
                contactsEntity.getEmail(),
                contactsEntity.getCreatedAt(),
                contactsEntity.getUpdatedAt(),
                contactsEntity.isDeleted()
        );
    }
}
