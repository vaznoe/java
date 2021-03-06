package com.company.app;

import com.company.app.dao.ContactDao;
import com.company.app.dao.ContactDaoImpl;
import com.company.app.model.Contact;
import com.company.app.resource.ContactResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class App extends Application<Configuration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String... args) throws Exception {
//        contactDaoImplTest();
        new App().run("server");
    }

    @Override
    public void run(Configuration configuration, Environment e) throws Exception {
        // Add the resource to the environment
        e.jersey().register(new ContactResource());

    }

    private static void contactDaoImplTest() {
        ContactDao contactDao = new ContactDaoImpl();

        System.out.println("#getContacts");
        getContacts(contactDao);

        System.out.println("\n#getContact");
        Contact tom = contactDao.getContact(0);
        System.out.println("Contact: [id: " + tom.getId() + ", Name: " + tom.getName() + "]");

        System.out.println("\n#createContact");
        Contact will = new Contact(4, "Will");
        int id = contactDao.createContact(will);
        System.out.println("id: " + id);
        getContacts(contactDao);

        System.out.println("\n#updateContact");
        contactDao.updateContact(new Contact(0, "Will"));
        getContacts(contactDao);

        System.out.println("\n#deleteContact");
        contactDao.deleteContact(will);
        getContacts(contactDao);
    }

    private static void getContacts(ContactDao contactDao) {
        List<Contact> contacts = contactDao.getContacts();
        contacts.forEach(s -> {
            System.out.println("Contact: [id: " + s.getId() + ", Name: " + s.getName() + "]");
        });
    }

}
