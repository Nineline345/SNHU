import java.util.HashMap;
import java.util.Map;

public class TaskService {
    private final Map<String, Task> tasks;

    public TaskService() {
        tasks = new HashMap<>();
    }

    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }

        String taskId = task.getTaskId();
        if (tasks.containsKey(taskId)) {
            throw new IllegalArgumentException("Task ID already exists");
        }

        tasks.put(taskId, task);
    }

    public void deleteTask(String taskId) {
        if (!tasks.containsKey(taskId)) {
            throw new IllegalArgumentException("Task ID was not found");
        }

        tasks.remove(taskId);
    }

    public void updateName(String taskId, String name) {
        findTask(taskId).setName(name);
    }

    public void updateDescription(String taskId, String description) {
        findTask(taskId).setDescription(description);
    }

    public Task findTask(String taskId) {
        Task task = tasks.get(taskId);

        if (task == null) {
            throw new IllegalArgumentException("Task ID was not found");
        }

        return task;
    }
}
