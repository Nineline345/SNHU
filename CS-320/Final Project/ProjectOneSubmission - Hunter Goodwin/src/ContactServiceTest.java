import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {
    private ContactService contactService;
    private Contact contact;

    @BeforeEach
    void setUp() {
        contactService = new ContactService();
        contact = new Contact("12345", "John", "Smith", "1234567890", "123 Main Street");
    }

    @Test
    void testAddContact() {
        contactService.addContact(contact);

        assertEquals(contact, contactService.getContact("12345"));
    }

    @Test
    void testAddContactRequiresUniqueId() {
        contactService.addContact(contact);

        Contact duplicateContact = new Contact("12345", "Jane", "Jones", "0987654321", "456 Oak Street");

        assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(duplicateContact);
        });
    }

    @Test
    void testCannotAddNullContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(null);
        });
    }

    @Test
    void testDeleteContact() {
        contactService.addContact(contact);
        contactService.deleteContact("12345");

        assertThrows(IllegalArgumentException.class, () -> {
            contactService.getContact("12345");
        });
    }

    @Test
    void testDeleteContactThatDoesNotExist() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.deleteContact("99999");
        });
    }

    @Test
    void testUpdateFirstName() {
        contactService.addContact(contact);
        contactService.updateFirstName("12345", "Jane");

        assertEquals("Jane", contactService.getContact("12345").getFirstName());
    }

    @Test
    void testUpdateLastName() {
        contactService.addContact(contact);
        contactService.updateLastName("12345", "Jones");

        assertEquals("Jones", contactService.getContact("12345").getLastName());
    }

    @Test
    void testUpdatePhone() {
        contactService.addContact(contact);
        contactService.updatePhone("12345", "0987654321");

        assertEquals("0987654321", contactService.getContact("12345").getPhone());
    }

    @Test
    void testUpdateAddress() {
        contactService.addContact(contact);
        contactService.updateAddress("12345", "456 Oak Street");

        assertEquals("456 Oak Street", contactService.getContact("12345").getAddress());
    }

    @Test
    void testUpdateContactThatDoesNotExist() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateFirstName("99999", "Jane");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateLastName("99999", "Jones");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updatePhone("99999", "0987654321");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateAddress("99999", "456 Oak Street");
        });
    }

    @Test
    void testUpdateWithInvalidFields() {
        contactService.addContact(contact);

        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateFirstName("12345", null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateLastName("12345", "Smithfield1");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updatePhone("12345", "12345");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateAddress("12345", "1234567890123456789012345678901");
        });
    }

    @Test
    void testContactIdDoesNotChangeThroughServiceUpdates() {
        contactService.addContact(contact);

        contactService.updateFirstName("12345", "Jane");
        contactService.updateLastName("12345", "Jones");
        contactService.updatePhone("12345", "0987654321");
        contactService.updateAddress("12345", "456 Oak Street");

        assertEquals("12345", contactService.getContact("12345").getContactId());
    }
}
