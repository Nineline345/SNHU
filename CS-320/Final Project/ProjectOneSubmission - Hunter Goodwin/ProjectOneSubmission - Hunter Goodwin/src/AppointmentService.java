import java.util.HashMap;
import java.util.Map;

public class AppointmentService {
    private final Map<String, Appointment> appointments;

    public AppointmentService() {
        appointments = new HashMap<>();
    }

    public void addAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment cannot be null");
        }

        String appointmentId = appointment.getAppointmentId();
        if (appointments.containsKey(appointmentId)) {
            throw new IllegalArgumentException("Appointment ID already exists");
        }

        appointments.put(appointmentId, appointment);
    }

    public void deleteAppointment(String appointmentId) {
        if (!appointments.containsKey(appointmentId)) {
            throw new IllegalArgumentException("Appointment ID was not found");
        }

        appointments.remove(appointmentId);
    }

    public Appointment findAppointment(String appointmentId) {
        Appointment appointment = appointments.get(appointmentId);

        if (appointment == null) {
            throw new IllegalArgumentException("Appointment ID was not found");
        }

        return appointment;
    }
}
