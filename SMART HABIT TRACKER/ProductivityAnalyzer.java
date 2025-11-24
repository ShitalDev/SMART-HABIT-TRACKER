import java.time.LocalDate;
import java.util.ArrayList;

public class ProductivityAnalyzer {
    public double getDailyScore(LocalDate date, HabitManager habitManager, CheckInManager checkInManager) {
        // implement logic described above
        ArrayList<Habit> allHabits = habitManager.getAllHabits();
        ArrayList<Habit> activeHabits = new ArrayList<>();

    for (Habit h : allHabits) {
        if (h.isActive()) {
            activeHabits.add(h);
        }
    }

    // If no active habits
    if (activeHabits.size() == 0) return 0;

    // Get check-ins for the date
    ArrayList<CheckInRecord> records = checkInManager.getCheckInsForDate(date);

    int completedCount = 0;

    // Check how many active habits were completed
    for (Habit habit : activeHabits) {

        for (CheckInRecord rec : records) {

            if (rec.getHabitId() == habit.getId() && rec.isCompleted()) {
                completedCount++;
                break; // prevent double-count
            }
        }
    }
    double score = ((double)completedCount / activeHabits.size()) * 100;
    return score;
    }

    public double getWeeklyScore(HabitManager habitManager, CheckInManager checkInManager) {
        // loop last 7 days calling getDailyScore()
        LocalDate today = LocalDate.now();
        double sum = 0;
        for(int i=0;i<7;i++){
            sum += getDailyScore(today.minusDays(i), habitManager, checkInManager);
        }
        double weaklyscore = sum/7;
        return weaklyscore;
    }

    public String getTrend(HabitManager habitManager, CheckInManager checkInManager) {
        // compare this week vs last week
        LocalDate today = LocalDate.now();
        double thisWeekScore = getWeeklyScore(habitManager, checkInManager);
        double lastWeekSum = 0;
        for (int i = 7; i < 14; i++) {
            lastWeekSum += getDailyScore(today.minusDays(i), habitManager, checkInManager);
        }
        double lastWeekScore = lastWeekSum / 7;

        if (thisWeekScore > lastWeekScore + 10)
            return "Improving";
        else if (thisWeekScore < lastWeekScore - 10)
            return "Declining";
        else
            return "Stable";
    }
}
