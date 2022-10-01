package com.alkemy.ong.domain.contacts;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class ContactsService {

    ContactsGateway contactsGateway;

    public List<Contacts> findAll() {
        return contactsGateway.findAll();
    }

    public Contacts createContact(Contacts contacts) {
        return contactsGateway.createContact(contacts);
    }

}
