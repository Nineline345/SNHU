import java.util.Date;

public class Appointment {
    private final String appointmentId;
    private final Date appointmentDate;
    private final String description;

    public Appointment(String appointmentId, Date appointmentDate, String description) {
        checkAppointmentId(appointmentId);
        checkAppointmentDate(appointmentDate);
        checkDescription(description);

        this.appointmentId = appointmentId;
        this.appointmentDate = new Date(appointmentDate.getTime());
        this.description = description;
    }

    private static void checkAppointmentId(String appointmentId) {
        if (appointmentId == null || appointmentId.length() > 10) {
            throw new IllegalArgumentException("Appointment ID is invalid");
        }
    }

    private static void checkAppointmentDate(Date appointmentDate) {
        if (appointmentDate == null || appointmentDate.before(new Date())) {
            throw new IllegalArgumentException("Appointment date is invalid");
        }
    }

    private static void checkDescription(String description) {
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Appointment description is invalid");
        }
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Date getAppointmentDate() {
        return new Date(appointmentDate.getTime());
    }

    public String getDescription() {
        return description;
    }
}
