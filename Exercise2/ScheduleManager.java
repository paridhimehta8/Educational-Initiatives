import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleManager {
    private static ScheduleManager instance;
    private final List<Task> tasks;
    private final List<IObserver> observers;

    private ScheduleManager() {
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static synchronized ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void registerObserver(IObserver observer) { observers.add(observer); }
    private void notifyObservers(String message) {
        for (IObserver observer : observers) {
            observer.update(message);
        }
    }

    public void addTask(Task newTask) throws TaskConflictException {
        for (Task existingTask : tasks) {
            if (newTask.overlapsWith(existingTask)) {
                String conflictMessage = "Error: Task conflicts with existing task \"" + existingTask.getDescription() + "\".";
                notifyObservers(conflictMessage);
                throw new TaskConflictException(conflictMessage);
            }
        }
        tasks.add(newTask);
        AppLogger.logInfo("Task added: " + newTask.getDescription());
        System.out.println("Task added successfully. No conflicts.");
    }

    public void removeTask(String description) throws TaskNotFoundException {
        boolean removed = tasks.removeIf(task -> task.getDescription().equalsIgnoreCase(description));
        if (!removed) {
            throw new TaskNotFoundException("Error: Task not found.");
        }
        AppLogger.logInfo("Task removed: " + description);
        System.out.println("Task removed successfully.");
    }

    public List<Task> viewTasks() {
        if (tasks.isEmpty()) {
            return new ArrayList<>();
        }
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getStartTime))
                .collect(Collectors.toList());
    }
}