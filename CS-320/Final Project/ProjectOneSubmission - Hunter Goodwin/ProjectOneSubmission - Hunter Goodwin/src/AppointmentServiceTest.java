import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppointmentServiceTest {
    private AppointmentService service;

    private Date futureDate() {
        return new Date(System.currentTimeMillis() + 86400000);
    }

    @BeforeEach
    void setUp() {
        service = new AppointmentService();
    }

    @Test
    void appointmentCanBeAdded() {
        Appointment appointment = new Appointment("A100", futureDate(), "Doctor appointment");

        service.addAppointment(appointment);

        assertEquals(appointment, service.findAppointment("A100"));
    }

    @Test
    void appointmentIdMustBeUnique() {
        Appointment firstAppointment = new Appointment("A100", futureDate(), "Doctor appointment");
        Appointment secondAppointment = new Appointment("A100", futureDate(), "Dentist appointment");

        service.addAppointment(firstAppointment);

        assertThrows(IllegalArgumentException.class, () -> {
            service.addAppointment(secondAppointment);
        });
    }

    @Test
    void nullAppointmentCannotBeAdded() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addAppointment(null);
        });
    }

    @Test
    void appointmentCanBeDeletedById() {
        Appointment appointment = new Appointment("A100", futureDate(), "Doctor appointment");

        service.addAppointment(appointment);
        service.deleteAppointment("A100");

        assertThrows(IllegalArgumentException.class, () -> {
            service.findAppointment("A100");
        });
    }

    @Test
    void missingAppointmentCannotBeDeleted() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteAppointment("A404");
        });
    }

    @Test
    void missingAppointmentCannotBeFound() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.findAppointment("A404");
        });
    }
}
