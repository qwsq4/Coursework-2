import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Schedule {
    private static Map<Integer, Task> taskList = new HashMap<>();

    public static Map<Integer, Task> getTaskList() {
        return taskList;
    }

    public static void checkTasks(LocalDateTime date) {
        System.out.println("Задачи на этот день");
        for (Task task : taskList.values()) {
            if (task.needDoToday(date));
            System.out.println(task.getName() + ", " + task.getId());
        }
    }

    public static void addToList(Task task) {
        taskList.put(task.getId(), task);
    }

    public static void deleteFromList(int id) {
        if (taskList.containsKey(id)) {
            taskList.remove(id);
            System.out.println("Задача " + taskList.get(id).getName() + " удалена!");
        } else System.out.println("Такой задачи нет");
    }
}
