import java.util.ArrayList;

public class HabitManager {
    private ArrayList<Habit> habits = new ArrayList<>();
    private int nextId = 1;
    private StorageManager storage = new StorageManager();


    public HabitManager() {
        habits = storage.loadHabits(); // <— load habits from file
    // Fix nextId so it continues correctly
        for (Habit h : habits) {
            if (h.getId() >= nextId) {
                nextId = h.getId() + 1;
            }
        }
    }   

    public String addHabit(String name){
        int id = nextId++;
        Habit newHabit = new Habit(id, name, true);
        habits.add(newHabit);
        storage.saveHabits(habits);

        return "habits added: " + newHabit; 

    }

    public String removeHabit(int id){
        for (int i = 0; i < habits.size(); i++) {
            if (habits.get(i).getId() == id) {
                Habit removed = habits.remove(i);
                storage.saveHabits(habits);
                return "Habit removed: " + removed;
            }
        }

        return "Habit with ID " + id + " not found.";
    }

    public ArrayList<Habit> getAllHabits(){
        return habits;
    }

    public String updateHabit(int id, String newName){
        for (Habit habit : habits) {
            if (habit.getId() == id) {
                habit.setName(newName);
                storage.saveHabits(habits);
                return "Habit updated: " + habit.toString();
            }
        }

        return "Habit with ID " + id + " not found.";
    }
}
