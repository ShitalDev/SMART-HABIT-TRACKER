import java.time.LocalDate;
import java.util.ArrayList;

public class CheckInManager {
    private ArrayList<CheckInRecord> checkIns = new ArrayList<>();
    private StorageManager storage = new StorageManager();

    public CheckInManager() {
        checkIns = storage.loadCheckIns();
    }

    public void markCompleted(int habitId){
        LocalDate today = LocalDate.now();
        for(CheckInRecord record : checkIns){
            if(record.getHabitId() == habitId && record.getDate().equals(today)){
                record.setCompleted(true);
                storage.saveCheckIns(checkIns);
                return; // Success
            }
        }
        CheckInRecord newRecord = new CheckInRecord(habitId, today, true);
        checkIns.add(newRecord);

        storage.saveCheckIns(checkIns);
    }

    public ArrayList<CheckInRecord> getTodayCheckIns(){
        ArrayList<CheckInRecord> todayList = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (CheckInRecord record : checkIns) {
            if (record.getDate().equals(today)) {
                 todayList.add(record);
                
            }
        }

    return todayList;
    }

    public ArrayList<CheckInRecord> getCheckInsForDate(LocalDate date) {
    ArrayList<CheckInRecord> result = new ArrayList<>();

        for (CheckInRecord record : checkIns) {
            if (record.getDate().equals(date)) {
                result.add(record);
            }
        }

        return result;
    }


}
