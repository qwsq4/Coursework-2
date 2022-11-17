import java.time.LocalDateTime;

public abstract class Task{
    public static class NoRepeat extends Task{
        public NoRepeat(String name, String desc, int type, LocalDateTime date) {
            super(name, desc, type, date);
        }
    }

    public static class DailyRepeat extends Task {
        public DailyRepeat(String name, String desc, int type, LocalDateTime date) {
            super(name, desc, type, date);
        }
        boolean needDoToday(LocalDateTime checkDay) {
            return date.getHour() <= checkDay.getHour();
        }
    }

    public static class WeeklyRepeat extends Task {
        public WeeklyRepeat(String name, String desc, int type, LocalDateTime date) {
            super(name, desc, type, date);
        }
        boolean needDoToday(LocalDateTime checkDay) {
            return date.getDayOfWeek() == checkDay.getDayOfWeek() &&
                    date.getHour() <= checkDay.getHour();
        }
    }

    public static class MonthlyRepeat extends Task {
        public MonthlyRepeat(String name, String desc, int type, LocalDateTime date) {
            super(name, desc, type, date);
        }
        boolean needDoToday(LocalDateTime checkDay) {
            return date.getDayOfMonth() == checkDay.getDayOfMonth() &&
                    date.getHour() <= checkDay.getHour();
        }
    }

    public static class YearlyRepeat extends Task {
        public YearlyRepeat(String name, String desc, int type, LocalDateTime date) {
            super(name, desc, type, date);
        }
        boolean needDoToday(LocalDateTime checkDay) {
            return date.getMonth() == checkDay.getMonth() &&
                    date.getDayOfMonth() == checkDay.getDayOfMonth() &&
                    date.getHour() <= checkDay.getHour();
        }
    }

    private String name;
    private String desc;
    private String type;
    private static LocalDateTime date;
    private static int count = 0;
    private final int id;


    public Task(String name, String desc, int type, LocalDateTime date) {
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


    boolean needDoToday(LocalDateTime checkDay) {
        return date.getYear() == checkDay.getYear() &&
                date.getMonth() == checkDay.getMonth() &&
                date.getDayOfMonth() == checkDay.getDayOfMonth() &&
                date.getHour() <= checkDay.getHour();
    }
}
