import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Task <T extends Task & Repeatability> {

    public class DailyRepeat extends Task implements Repeatability{
        public DailyRepeat(String name, String desc, int type, Date date) {
            super(name, desc, type, date);
        }
        private boolean needDoToday(Date checkDay) {
            return date.getHours() <= checkDay.getHours();
        }
    }

    public class WeeklyRepeat extends Task implements Repeatability {
        public WeeklyRepeat(String name, String desc, int type, Date date) {
            super(name, desc, type, date);
        }
        private boolean needDoToday(Date checkDay) {
            return getDayNumber(date) == getDayNumber(checkDay) &&
                    date.getHours() <= checkDay.getHours();
        }
    }

    public class MonthlyRepeat extends Task implements Repeatability {
        public MonthlyRepeat(String name, String desc, int type, Date date) {
            super(name, desc, type, date);
        }
        private boolean needDoToday(Date checkDay) {
            return date.getDay() == checkDay.getDay() &&
                    date.getHours() <= checkDay.getHours();
        }
    }

    public class YearlyRepeat extends Task implements Repeatability{
        public YearlyRepeat(String name, String desc, int type, Date date) {
            super(name, desc, type, date);
        }
        private boolean needDoToday(Date checkDay) {
            return date.getMonth() == checkDay.getMonth() &&
                    date.getDay() == checkDay.getDay() &&
                    date.getHours() <= checkDay.getDay();
        }
    }

    private String name;
    private String desc;
    private String type;
    private Date date;
    private static int count = 0;
    private final int id;
    private static Map<Integer, Task<?>> taskList = new HashMap<>();

    public Task(String name, String desc, int type, Date date) {
        this.name = name;
        this.desc = desc;
        if (type < 3) {
            switch (type) {
                case 1:
                    this.type = "Личная";
                    break;
                case 2:
                    this.type = "Рабочая";
                    break;
            }
        } else this.type = "Не указано";
        this.date = date;
        this.id = count;
        count++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public static int getCount() {
        return count;
    }

    public static Map<Integer, Task<?>> getTaskList() {
        return taskList;
    }

    private boolean needDoToday(Date checkDay) {
        return date.getYear() == checkDay.getYear() &&
                date.getMonth() == checkDay.getMonth() &&
                date.getDay() == checkDay.getDay() &&
                date.getHours() <= checkDay.getDay();
    }

    public static void checkTasks(Date date) {
        System.out.println("Задачи на этот день");
        for (Task task : taskList.values()) {
            if (task.needDoToday(date));
            System.out.println(task.getName() + ", " + task.getId());
        }
    }

    public static void addToList(Task<?> task) {
        taskList.put(task.getId(), task);
    }

    public static void deleteFromList(int id) {
        if (taskList.containsKey(id)) {
            taskList.remove(id);
            System.out.println("Задача " + taskList.get(id).getName() + " удалена!");
        } else System.out.println("Такой задачи нет");
    }

    public static int getDayNumber(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }
}
