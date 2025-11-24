import java.time.LocalDate;

public class CheckInRecord {
    private int habitId;
    private LocalDate date;
    private boolean completed;

    public CheckInRecord(int habitId, LocalDate date, boolean completed) {
        this.habitId = habitId;
        this.date = date;
        this.completed = completed;
    }

    public int getHabitId() {
        return habitId;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return habitId + "," + date.toString() + "," + completed;
    }
}
