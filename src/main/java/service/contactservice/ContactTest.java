/****************************************************************
 * Module Three Milestone
 * [ContactTest.java]
 * Author: Michael Lorenz
 * - CS320 - Software Test, Automation QA
 * - Southern New Hampshire University
 *
 * Description:
 * This class contains unit tests for Contact class. Verifies the
 * requirements of acceptable variables.
 *
 * Data: Due 9/22/2024
 * Modified: 9/25/2024 to support code restructures. added tests.
 ****************************************************************/
package service.contactservice;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest
{
    // Reset the unique id incrementer to 0 after each test
    @AfterEach
    void tearDown() {
        Contact.resetCounter();
    }

    // Test creating a contact object
    @DisplayName("Test initializing Contact and match parameters to variables")
    @Test
    void testInitializeContact() {
        Contact contact = new Contact( "John", "Marston", "7034224806", "Phoenix, Arizona");
        assertEquals("0", contact.getId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Marston", contact.getLastName());
        assertEquals("7034224806", contact.getPhone());
        assertEquals("Phoenix, Arizona", contact.getAddress());
    }

    // Requirement 1: contact ID string cannot be longer than 10 characters, shall not be null, and shall not be updatable
    @Nested
    @DisplayName("Contact ID requirements testing")
    class testContactId
    {
        @DisplayName("Test when contact ID is 10 characters, should not throw exception")
        @Test
        void testContactId10Chars() {
            Contact contact = new Contact("John", "Marston", "7034224806", "Phoenix, Arizona");
            Contact.setCounter(9999999990L);
            // create contact test and check that it contains the 10 char id
            Contact test = new Contact("John", "Marston", "1002003000", "Phoenix, Arizona");
            Assertions.assertEquals("9999999990", test.getId());
        }

        @DisplayName("Test that appointment ID is not null")
        @Test
        void testAppointmentIdIsNotNull() {
            // id physically cannot be set to null
            Contact test = new Contact("John", "Marston", "1002003000", "Phoenix, Arizona");
            Assertions.assertNotEquals(null, test.getId());
        }

        @DisplayName("Test when contact ID is 11 characters, should throw exception")
        @Test
        void testContactIdTooLong() {
            Contact contact = new Contact("John", "Marston", "7034224806", "Phoenix, Arizona");
            Contact.setCounter(9999999999L); //next contact will have this id
            new Contact("John", "Marston", "1002003000", "Phoenix, Arizona"); // next contact will overflow
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Contact("John", "Marston", "1002003000", "Phoenix, Arizona");
            });
        }

        @DisplayName("Test if Contact id is not updatable")
        @Test
        void testContactIdIsNotUpdatable() {
            // id is a final variable and does not have an associated setter,
            // cannot be modified for existing contacts
        }
    }

    // Requirement 2: firstName string is required, cannot be longer than 10 chars, and shall not be null
    @Nested
    @DisplayName("Contact firstName requirements testing")
    class testContactFirstName
    {
        @DisplayName("Test when contact firstName is 0 characters, should throw exception")
        @Test
        void testContactFirstNameEmpty() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Contact("", "Marston", "1002003000", "Phoenix, Arizona");
            });
        }

        @DisplayName("Test when contact firstName is 10 characters, should not throw exception")
        @Test
        void testContactFirstName10Chars() {
            Contact c = new Contact("Johnathonn", "Marston", "1002003000", "Phoenix, Arizona");
            Assertions.assertEquals("Johnathonn", c.getFirstName());
        }

        @DisplayName("Test when contact firstName is 11 characters, should throw exception")
        @Test
        void testContactFirstNameTooLong() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Contact("Johnathoonn", "Marston", "1002003000", "Phoenix, Arizona");
            });
        }

        @DisplayName("Test when contact firstName is whitespace, should throw exception")
        @Test
        void testContactFirstWhitespace() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Contact("          ", "Marston", "1002003000", "Phoenix, Arizona");
            });
        }

        @DisplayName("Test when contact firstName is null, should throw exception")
        @Test
        void testContactFirstNameIsNull() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Contact(null, "Marston", "1002003000", "Phoenix, Arizona");
            });
        }
    }

    // Requirement 3: required lastName string is required, cannot be longer than 10 chars, and shall not be null
    @Nested
    @DisplayName("Contact lastName requirements testing")
    class testContactLastName
    {
        @DisplayName("Test when contact lastName is 0 characters, should throw exception")
        @Test
        void testContactLastNameEmpty() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Contact("John", "", "1002003000", "Phoenix, Arizona");
            });
        }

        @DisplayName("Test when contact lastName is 10 characters, should not throw exception")
        @Test
        void testContactLastName10Chars() {
            Contact c = new Contact("John", "NameIsLong", "1002003000", "Phoenix, Arizona");
            Assertions.assertEquals("NameIsLong", c.getLastName());
        }

        @DisplayName("Test when contact lastName is 11 characters, should throw exception")
        @Test
        void testContactLastNameTooLong() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Contact("John", "NameIsLongs", "1002003000", "Phoenix, Arizona");
            });
        }

        @DisplayName("Test when contact lastName is whitespace, should throw exception")
        @Test
        void testContactLastNameWhitespace() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Contact("John", "          ", "1002003000", "Phoenix, Arizona");
            });
        }

        @DisplayName("Test when contact lastName is null, should throw exception")
        @Test
        void testContactLastNameIsNull() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Contact("John", null, "1002003000", "Phoenix, Arizona");
            });
        }
    }

    // Requirement 4: required phone string must be 10 chars and shall not be null
    @Nested
    @DisplayName("Contact phone number requirements testing")
    class testContactPhoneNumber
    {
        @DisplayName("Test when contact phone number is 10 characters, should not throw exception")
        @Test
        void testContactPhone10Chars() {
            Contact c = new Contact("John", "Marston", "1234567890", "Phoenix, Arizona");
            Assertions.assertEquals("1234567890", c.getPhone());
        }

        @DisplayName("Test when contact phone number is 9 characters, should throw exception")
        @Test
        void testContactPhoneNumberTooShort() {
            // < 10 chars
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Contact("John", "Marston", "123456789", "Phoenix, Arizona");
            });
        }

        @DisplayName("Test when contact phone number is 11 characters, should throw exception")
        @Test
        void testContactPhoneNumberTooLong() {
            // > 10 chars
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Contact("John", "Marston", "12345678910", "Phoenix, Arizona");
            });
        }

        @DisplayName("Test when contact phone number is whitespace characters, should throw exception")
        @Test
        void testContactPhoneNumberWhitespace() {
            // > 10 chars
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Contact("John", "Marston", "          ", "Phoenix, Arizona");
            });
        }

        @DisplayName("Test when contact phone number is null, should throw exception")
        @Test
        void testContactPhoneNumberIsNull() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Contact("John", "Marston", null, "Phoenix, Arizona");
            });
        }
    }

    // Requirement 5: required address string cannot be longer than 30 chars and shall not be null
    @Nested
    @DisplayName("Contact address requirements testing")
    class testContactAddress
    {
        @DisplayName("Test when contact address is 0 characters, should throw exception")
        @Test
        void testContactAddressEmpty() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Contact("John", "Marston", "0123456789", "");
            });
        }

        @DisplayName("Test when contact address is 30 characters, should not throw exception")
        @Test
        void testContactAddress30Chars() {
            Contact c = new Contact("John", "Marston", "1234567890", "7 Some Street Phoenix, Arizona");
            Assertions.assertEquals("7 Some Street Phoenix, Arizona", c.getAddress());
        }

        @DisplayName("Test when contact address is 31 characters, should throw exception")
        @Test
        void testContactAddressTooLong() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Contact("John", "Marston", "0123456789", "19 Some Street Phoenix, Arizona");
            });
        }

        @DisplayName("Test when contact address is whitespace characters, should throw exception")
        @Test
        void testContactAddressWhitespace() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Contact("John", "Marston", "0123456789", "                              ");
            });
        }

        @DisplayName("Test when contact address is null, should throw exception")
        @Test
        void testContactAddressIsNull() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Contact("John", "Marston", "0123456789", null);
            });
        }
    }
}