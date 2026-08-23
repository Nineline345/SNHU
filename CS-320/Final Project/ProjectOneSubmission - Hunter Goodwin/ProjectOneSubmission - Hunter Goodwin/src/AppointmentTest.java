import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class AppointmentTest {

    private Date futureDate() {
        return new Date(System.currentTimeMillis() + 86400000);
    }

    private Date pastDate() {
        return new Date(System.currentTimeMillis() - 86400000);
    }

    @Test
    void appointmentCanBeCreated() {
        Date date = futureDate();
        Appointment appointment = new Appointment("A100", date, "Doctor appointment");

        assertEquals("A100", appointment.getAppointmentId());
        assertEquals(date, appointment.getAppointmentDate());
        assertEquals("Doctor appointment", appointment.getDescription());
    }

    @Test
    void appointmentIdCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(null, futureDate(), "Doctor appointment");
        });
    }

    @Test
    void appointmentIdCannotBeLongerThanTenCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("APPT1234567", futureDate(), "Doctor appointment");
        });
    }

    @Test
    void appointmentDateCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("A100", null, "Doctor appointment");
        });
    }

    @Test
    void appointmentDateCannotBeInThePast() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("A100", pastDate(), "Doctor appointment");
        });
    }

    @Test
    void descriptionCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("A100", futureDate(), null);
        });
    }

    @Test
    void descriptionCannotBeLongerThanFiftyCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("A100", futureDate(), "This description is intentionally longer than fifty characters total");
        });
    }

    @Test
    void appointmentIdDoesNotChange() {
        Appointment appointment = new Appointment("A100", futureDate(), "Doctor appointment");

        assertEquals("A100", appointment.getAppointmentId());
    }
}
