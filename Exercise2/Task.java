import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private Priority priority;

    public Task(String description, LocalTime startTime, LocalTime endTime, Priority priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
    }

    public String getDescription() { return description; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }

    public boolean overlapsWith(Task other) {
        return this.startTime.isBefore(other.endTime) && this.endTime.isAfter(other.startTime);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return String.format("%s-%s: %s [%s]",
                startTime.format(formatter),
                endTime.format(formatter),
                description,
                priority);
    }
}