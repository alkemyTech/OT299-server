package com.alkemy.ong.domain.contacts;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface ContactsGateway {

    List<Contacts> findAll();

    Contacts createContact(Contacts contacts);

}
