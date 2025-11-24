import java.util.Scanner;
import java.time.LocalDate;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        HabitManager habitManager = new HabitManager();
        CheckInManager checkInManager = new CheckInManager();
        ProductivityAnalyzer analyzer = new ProductivityAnalyzer();

        while (true) {
            showMenu();
            int choice = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            switch (choice) {

                case 1:
                    System.out.print("Enter habit name: ");
                    String name = sc.nextLine();
                    System.out.println(habitManager.addHabit(name));
                    break;

                case 2:
                    System.out.println("==== All Habits ====");
                    System.out.println(habitManager.getAllHabits());
                    break;

                case 3:
                    System.out.print("Enter habit ID to update: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();
                    System.out.println(habitManager.updateHabit(uid, newName));
                    break;

                case 4:
                    System.out.print("Enter habit ID to remove: ");
                    int rid = sc.nextInt();
                    System.out.println(habitManager.removeHabit(rid));
                    break;

                case 5:
                    System.out.print("Enter habit ID to mark completed: ");
                    int mid = sc.nextInt();
                    checkInManager.markCompleted(mid);
                    System.out.println("Marked completed.");
                    break;

                case 6:
                    double daily = analyzer.getDailyScore(LocalDate.now(), habitManager, checkInManager);
                    System.out.println("Today's Productivity Score: " + daily);
                    break;

                case 7:
                    double weekly = analyzer.getWeeklyScore(habitManager, checkInManager);
                    System.out.println("Weekly Score: " + weekly);
                    break;

                case 8:
                    String trend = analyzer.getTrend(habitManager, checkInManager);
                    System.out.println("Trend: " + trend);
                    break;

                case 9:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void showMenu() {
        System.out.println("\n====== HABIT TRACKER ======");
        System.out.println("1. Add Habit");
        System.out.println("2. View Habits");
        System.out.println("3. Update Habit");
        System.out.println("4. Remove Habit");
        System.out.println("5. Mark Habit Completed");
        System.out.println("6. View Today's Productivity Score");
        System.out.println("7. View Weekly Score");
        System.out.println("8. View Trend");
        System.out.println("9. Exit");
        System.out.print("Enter choice: ");
    }
}
