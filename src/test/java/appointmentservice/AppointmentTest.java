/****************************************************************
 * Module Five Milestone
 * [AppointmentTest.java]
 * Author: Michael Lorenz
 * - CS320 - Software Test, Automation QA
 * - Southern New Hampshire University
 *
 * Description:
 * This class contains unit tests for Appointment class. Verifies the
 * requirements of acceptable variables.
 *
 * Data: Due 9/29/2024
 ****************************************************************/
package appointmentservice;

import org.junit.jupiter.api.*;
import service.appointmentservice.BasicIdGenerator;
import service.appointmentservice.Appointment;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest
{
    Date date;
    @BeforeEach
    void setUp() {
        // use date 100000ms after current system time for each date (unless otherwise specified)
        date = new Date(new Date().getTime() + 100000);
    }

    @AfterEach
    void tearDown() {
        BasicIdGenerator.resetCounters(); // Reset the unique id incrementer to 0 after each test
    }

    // Test creating an appointment object
    @DisplayName("Test initializing Appointment and match parameters to variables")
    @Test
    void testInitializeAppointment() {
        Appointment appointment = new Appointment( date, "have a meeting");
        // fields should match given parameters
        assertEquals("0", appointment.getId());
        assertEquals(date, appointment.getDate());
        assertEquals("have a meeting", appointment.getDescription());
    }

    // Requirement 1: appointment ID string cannot be longer than 10 characters, shall not be null, and shall not be updatable
    @Nested
    @DisplayName("Appointment ID requirements testing")
    class testAppointmentId {
        @DisplayName("Test when appointment ID is 10 characters, should not throw exception")
        @Test
        void testAppointmentId10Chars() {
            // create an appointment to initialize the id counter
            Appointment appointment = new Appointment(date, "have a meeting");
            // set the current idCounter to a 10 character long, next created object will use this id
            BasicIdGenerator.setCounter(appointment.getClass(), 9999999999L);
            // create appointment using above id and check that it contains that specified id.
            Appointment test = new Appointment(date, "description");
            assertEquals("9999999999", test.getId());
        }

        @DisplayName("Test when appointment ID is 11 characters, should throw exception")
        @Test
        void testAppointmentIdTooLong() {
            // initialize the id counter to 0
            Appointment appointment = new Appointment(date, "have a meeting");
            // set id counter to 11 character long, next created object should throw exception
            BasicIdGenerator.setCounter(appointment.getClass(), 99999999999L);
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Appointment(date, "description"));
        }

        @DisplayName("Test that appointment ID is not null")
        @Test
        void testAppointmentIdIsNotNull() {
            // id physically cannot be set to null
            Appointment appointment = new Appointment(date, "description");
            Assertions.assertNotEquals(null, appointment.getId());
        }

        @DisplayName("Test if Appointment id is not updatable")
        @Test
        void testAppointmentIdIsNotUpdatable() {
            // appointmentID is a final variable and does not have an associated setter,
            // cannot be modified
        }
    }

    // Requirement 2: required date shall not be null and shall not be in the past
    @Nested
    @DisplayName("Appointment date requirements testing")
    class testAppointmentDate
    {
        @DisplayName("Test when appointment date is not in the past, should not throw exception")
        @Test
        void testAppointmentDateInFuture() {
            // use date 1 ms after current system time
            date = new Date(new Date().getTime() + 1);
            // date should match given date when initializing with this date
            Appointment app = new Appointment(date, "have a meeting");
            assertEquals(date, app.getDate());;
        }

        @DisplayName("Test when appointment date is in the past, should throw exception")
        @Test
        void testAppointmentDateInPast() {
            // use date 1 ms behind current system time
            date = new Date(new Date().getTime() - 1);
            // should throw exception when initializing with this date
            Assertions.assertThrows(IllegalArgumentException.class, () ->
                    new Appointment(date, "have a meeting"));
        }

        @DisplayName("Test when appointment date is null, should throw exception")
        @Test
        void testAppointmentDateIsNull() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Appointment(null, "my date is null");
            });
        }
    }

    // Requirement 3: required description string cannot be longer than 50 chars and shall not be null
    @Nested
    @DisplayName("Appointment description requirements testing")
    class testAppointmentDescription
    {
        @DisplayName("Test when appointment description is 0 characters, should throw exception")
        @Test
        void testAppointmentDescriptionEmpty() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Appointment(date, "");
            });
        }

        @DisplayName("Test when appointment description is 50 characters, should not throw exception")
        @Test
        void testAppointmentDescription50Chars() {
            Appointment appointment = new Appointment(date, "You need to drink at least 8 cups of water a day..");
            Assertions.assertEquals("You need to drink at least 8 cups of water a day..", appointment.getDescription());
        }

        @DisplayName("Test when appointment description is 51 characters, should throw exception")
        @Test
        void testAppointmentDescriptionTooLong() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Appointment(date, "You need to drink at least 80 cups of water a day..");
            });
        }

        @DisplayName("Test when appointment description is all whitespace characters, should throw exception")
        @Test
        void testAppointmentDescriptionWhitespace() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Appointment(date, "                                                  ");
            });
        }

        @DisplayName("Test when appointment description has leading whitespace characters, should throw exception")
        @Test
        void testAppointmentDescriptionLeadingWhitespace() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Appointment(date, " leading whitespace");
            });
        }

        @DisplayName("Test when appointment description has trailing whitespace characters, should throw exception")
        @Test
        void testAppointmentDescriptionTrailingWhitespace() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Appointment(date, "trailing whitespace ");
            });
        }

        @DisplayName("Test when appointment description is null, should throw exception")
        @Test
        void testAppointmentDescriptionIsNull() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                new Appointment(date, null);
            });
        }
    }
}