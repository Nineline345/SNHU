import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskServiceTest {
    private TaskService service;

    @BeforeEach
    void setUp() {
        service = new TaskService();
    }

    @Test
    void taskCanBeAdded() {
        Task task = new Task("T100", "Study", "Finish Work");

        service.addTask(task);

        assertEquals(task, service.findTask("T100"));
    }

    @Test
    void taskIdMustBeUnique() {
        Task firstTask = new Task("T100", "Study", "Finish Work");
        Task secondTask = new Task("T100", "Review", "Review the test files");

        service.addTask(firstTask);

        assertThrows(IllegalArgumentException.class, () -> {
            service.addTask(secondTask);
        });
    }

    @Test
    void nullTaskCannotBeAdded() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addTask(null);
        });
    }

    @Test
    void taskCanBeDeletedById() {
        Task task = new Task("T100", "Study", "Finish Work");

        service.addTask(task);
        service.deleteTask("T100");

        assertThrows(IllegalArgumentException.class, () -> {
            service.findTask("T100");
        });
    }

    @Test
    void missingTaskCannotBeDeleted() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteTask("T404");
        });
    }

    @Test
    void nameCanBeUpdatedById() {
        Task task = new Task("T100", "Study", "Finish Work");

        service.addTask(task);
        service.updateName("T100", "Review");

        assertEquals("Review", service.findTask("T100").getName());
    }

    @Test
    void descriptionCanBeUpdatedById() {
        Task task = new Task("T100", "Study", "Finish Work");

        service.addTask(task);
        service.updateDescription("T100", "Review the test files");

        assertEquals("Review the test files", service.findTask("T100").getDescription());
    }

    @Test
    void missingTaskCannotBeUpdated() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateName("T404", "Review");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            service.updateDescription("T404", "Review the test files");
        });
    }

    @Test
    void invalidTaskUpdatesAreRejected() {
        Task task = new Task("T100", "Study", "Finish Work");

        service.addTask(task);

        assertThrows(IllegalArgumentException.class, () -> {
            service.updateName("T100", null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            service.updateName("T100", "This task name is too long");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            service.updateDescription("T100", null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            service.updateDescription("T100", "This description is intentionally longer than fifty characters total");
        });
    }
}
