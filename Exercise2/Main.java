import java.util.List;
import java.util.Scanner;

public class Main {
    private static final ScheduleManager scheduleManager = ScheduleManager.getInstance();
    private static final TaskFactory taskFactory = new TaskFactory();

    public static void main(String[] args) {
        AppLogger.logInfo("Astronaut Daily Schedule Organizer starting up.");
        scheduleManager.registerObserver(new SchedulingConflictObserver());
        
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("--- Astronaut Daily Schedule Organizer ---");
        printHelp();

        while (isRunning) {
            System.out.print("\nEnter command: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                isRunning = false;
                continue;
            }
            if (input.isEmpty()) continue;
            
            processCommand(input);
        }

        System.out.println("Exiting application. Goodbye!");
        AppLogger.logInfo("Application shut down gracefully.");
        scanner.close();
    }

    private static void processCommand(String command) {
        String[] parts = command.split("\\s+", 2);
        String action = parts[0].toLowerCase();
        
        try {
            switch (action) {
                case "add": handleAdd(parts[1]); break;
                case "remove": handleRemove(parts[1]); break;
                case "view": handleView(); break;
                case "help": printHelp(); break;
                default: System.out.println("Error: Unknown command. Type 'help'.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Missing arguments for command '" + action + "'. Type 'help'.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleAdd(String args) throws InvalidTimeFormatException, TaskConflictException {
        String[] params = args.split("\"");
        if (params.length < 2) {
            System.out.println("Error: Invalid format. Use: add \"Description\" HH:mm HH:mm PRIORITY");
            return;
        }
        String description = params[1];
        String[] otherParams = params[2].trim().split("\\s+");

        Task task = taskFactory.createTask(description, otherParams[0], otherParams[1], otherParams[2]);
        scheduleManager.addTask(task);
    }

    private static void handleRemove(String args) throws TaskNotFoundException {
        String description = args.replaceAll("\"", "");
        scheduleManager.removeTask(description);
    }

    private static void handleView() {
        List<Task> tasks = scheduleManager.viewTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
        } else {
            System.out.println("\n--- Daily Schedule ---");
            tasks.forEach(System.out::println);
            System.out.println("----------------------");
        }
    }

    private static void printHelp() {
        System.out.println("\nAvailable Commands:");
        System.out.println("  add \"<description>\" <start_time> <end_time> <priority>");
        System.out.println("    - Example: add \"Morning Exercise\" 07:00 08:00 High");
        System.out.println("  remove \"<description>\"");
        System.out.println("  view");
        System.out.println("  exit");
    }
}