import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class TaskFactory {
    public Task createTask(String description, String startTimeStr, String endTimeStr, String priorityStr) throws InvalidTimeFormatException {
        try {
            LocalTime startTime = LocalTime.parse(startTimeStr);
            LocalTime endTime = LocalTime.parse(endTimeStr);
            Priority priority = Priority.valueOf(priorityStr.toUpperCase());
            
            if (endTime.isBefore(startTime)) {
                throw new InvalidTimeFormatException("End time cannot be before start time.");
            }

            return new Task(description, startTime, endTime, priority);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException("Invalid time format. Please use HH:mm.");
        } catch (IllegalArgumentException e) {
            throw new InvalidTimeFormatException("Invalid priority. Use HIGH, MEDIUM, or LOW.");
        }
    }
}