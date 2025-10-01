public class SchedulingConflictObserver implements IObserver {
    @Override
    public void update(String message) {
        System.out.println("NOTIFICATION: " + message);
    }
}