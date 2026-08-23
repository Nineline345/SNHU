import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    void taskCanBeCreated() {
        Task task = new Task("T100", "Study", "Finish Work");

        assertEquals("T100", task.getTaskId());
        assertEquals("Study", task.getName());
        assertEquals("Finish Work", task.getDescription());
    }

    @Test
    void taskIdCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task(null, "Study", "Finish Work");
        });
    }

    @Test
    void taskIdCannotBeLongerThanTenCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("TASK1234567", "Study", "Finish Work");
        });
    }

    @Test
    void nameCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("T100", null, "Finish Work");
        });
    }

    @Test
    void nameCannotBeLongerThanTwentyCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("T100", "This task name is too long", "Finish Work");
        });
    }

    @Test
    void descriptionCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("T100", "Study", null);
        });
    }

    @Test
    void descriptionCannotBeLongerThanFiftyCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("T100", "Study", "This description is intentionally longer than fifty characters total");
        });
    }

    @Test
    void nameCanBeUpdated() {
        Task task = new Task("T100", "Study", "Finish Work");

        task.setName("Review");

        assertEquals("Review", task.getName());
    }

    @Test
    void descriptionCanBeUpdated() {
        Task task = new Task("T100", "Study", "Finish Work");

        task.setDescription("Review the test files");

        assertEquals("Review the test files", task.getDescription());
    }

    @Test
    void invalidUpdatesAreRejected() {
        Task task = new Task("T100", "Study", "Finish Work");

        assertThrows(IllegalArgumentException.class, () -> {
            task.setName(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            task.setName("This task name is too long");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            task.setDescription(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            task.setDescription("This description is intentionally longer than fifty characters total");
        });
    }

    @Test
    void taskIdDoesNotChangeAfterUpdates() {
        Task task = new Task("T100", "Study", "Finish Work");

        task.setName("Review");
        task.setDescription("Review the test files");

        assertEquals("T100", task.getTaskId());
    }
}
