public class Task {
    private final String taskId;
    private String name;
    private String description;

    public Task(String taskId, String name, String description) {
        checkTaskId(taskId);
        checkName(name);
        checkDescription(description);

        this.taskId = taskId;
        this.name = name;
        this.description = description;
    }

    private static void checkTaskId(String taskId) {
        if (taskId == null || taskId.length() > 10) {
            throw new IllegalArgumentException("Task ID is invalid");
        }
    }

    private static void checkName(String name) {
        if (name == null || name.length() > 20) {
            throw new IllegalArgumentException("Task name is invalid");
        }
    }

    private static void checkDescription(String description) {
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Task description is invalid");
        }
    }

    public String getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkName(name);
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        checkDescription(description);
        this.description = description;
    }
}
