import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ContactTest {

    @Test
    void testContactCreatedSuccessfully() {
        Contact contact = new Contact("1234567890", "John", "Smith", "1234567890", "123 Main Street");

        assertEquals("1234567890", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Smith", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Main Street", contact.getAddress());
    }

    @Test
    void testContactIdCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, "John", "Smith", "1234567890", "123 Main Street");
        });
    }

    @Test
    void testContactIdCannotBeLongerThanTenCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", "John", "Smith", "1234567890", "123 Main Street");
        });
    }

    @Test
    void testFirstNameCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", null, "Smith", "1234567890", "123 Main Street");
        });
    }

    @Test
    void testFirstNameCannotBeLongerThanTenCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "Johnathan11", "Smith", "1234567890", "123 Main Street");
        });
    }

    @Test
    void testLastNameCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", null, "1234567890", "123 Main Street");
        });
    }

    @Test
    void testLastNameCannotBeLongerThanTenCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Smithfield1", "1234567890", "123 Main Street");
        });
    }

    @Test
    void testPhoneCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Smith", null, "123 Main Street");
        });
    }

    @Test
    void testPhoneMustBeExactlyTenDigits() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Smith", "123456789", "123 Main Street");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Smith", "12345678901", "123 Main Street");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Smith", "12345abc90", "123 Main Street");
        });
    }

    @Test
    void testAddressCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Smith", "1234567890", null);
        });
    }

    @Test
    void testAddressCannotBeLongerThanThirtyCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Smith", "1234567890", "1234567890123456789012345678901");
        });
    }

    @Test
    void testSettersUpdateValidFields() {
        Contact contact = new Contact("12345", "John", "Smith", "1234567890", "123 Main Street");

        contact.setFirstName("Jane");
        contact.setLastName("Jones");
        contact.setPhone("0987654321");
        contact.setAddress("456 Oak Street");

        assertEquals("Jane", contact.getFirstName());
        assertEquals("Jones", contact.getLastName());
        assertEquals("0987654321", contact.getPhone());
        assertEquals("456 Oak Street", contact.getAddress());
    }

    @Test
    void testContactIdDoesNotChangeAfterUpdates() {
        Contact contact = new Contact("12345", "John", "Smith", "1234567890", "123 Main Street");

        contact.setFirstName("Jane");
        contact.setLastName("Jones");
        contact.setPhone("0987654321");
        contact.setAddress("456 Oak Street");

        assertEquals("12345", contact.getContactId());
    }
}
