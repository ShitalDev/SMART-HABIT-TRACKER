import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.io.FileReader;
import java.util.ArrayList;

public class StorageManager {
    private static final String FILE_PATH = "habits.txt";
    private static final String CHECKINS_FILE = "checkins.txt";

    void saveHabits(ArrayList<Habit> habits){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for(Habit habit : habits){
                String line = habit.getId() + "," + habit.getName() + "," + habit.isActive();
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ArrayList<Habit> loadHabits(){
        ArrayList<Habit> habits = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                boolean isActive = Boolean.parseBoolean(parts[2]);

                Habit habit = new Habit(id, name, isActive);
                habits.add(habit);
            }
        } catch (Exception e) {
            e.printStackTrace();
       
    }
        return habits;
    }

    public void saveCheckIns(ArrayList<CheckInRecord> checkIns){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(CHECKINS_FILE))){
            for(CheckInRecord rec : checkIns){
                bw.write(rec.toString());
                bw.newLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<CheckInRecord> loadCheckIns(){
        ArrayList<CheckInRecord> list = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(CHECKINS_FILE))) {
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(",");
                int habitid = Integer.parseInt(parts[0]);
                LocalDate date = LocalDate.parse(parts[1]);
                boolean complete = Boolean.parseBoolean(parts[2]);

                CheckInRecord record = new CheckInRecord(habitid, date, complete);
                 list.add(record);
                }
            } catch (Exception e) {
            e.printStackTrace();
    }
        return list;
    }
}


