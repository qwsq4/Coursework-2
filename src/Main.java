import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static void inputTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.next();
        System.out.print("Введите описание задачу: ");
        String taskDesc = scanner.next();
        System.out.print("Укажите дату выполнения(в формате день.месяц.год/час:минута): ");
        String stringDate = scanner.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy/HH:mm");
        LocalDateTime date = LocalDateTime.parse(stringDate, formatter);
        System.out.print("Укажите тип задачи(1 - если личная, 2 - если рабочая, по умолчанию выставляется личная): ");
        int taskType = scanner.nextInt();
        System.out.print("Укажите повторяемость задачи(0 - неповторяемая, 1 - ежедневная, 2 - еженедельная, " +
                "3 - ежемесячная, 4 - ежегодная): ");
        int taskRepeatability = scanner.nextInt();
        switch (taskRepeatability) {
            case 0:
                Schedule.addToList(new Task.NoRepeat(taskName, taskDesc, taskType, date));
                break;
            case 1:
                Schedule.addToList(new Task.DailyRepeat(taskName, taskDesc, taskType, date));
                break;
            case 2:
                Schedule.addToList(new Task.WeeklyRepeat(taskName, taskDesc, taskType, date));
                break;
            case 3:
                Schedule.addToList(new Task.MonthlyRepeat(taskName, taskDesc, taskType, date));
                break;
            case 4:
                Schedule.addToList(new Task.YearlyRepeat(taskName, taskDesc, taskType, date));
                break;
        }
        System.out.println("Задача " + taskName + " добавлена!");
    }

    public static void deleteTask(Scanner scanner) {
        System.out.print("Введите ID задачи: ");
        Schedule.deleteFromList(scanner.nextInt());
    }

    public static void checkTasks(Scanner scanner) {
        System.out.print("Введите дату: ");
        String stringDate = scanner.next() + "/00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy/HH:mm");
        LocalDateTime date = LocalDateTime.parse(stringDate, formatter);
        Schedule.checkTasks(date);
    }

    private static void printMenu() {
        System.out.println("1. Добавить задачу");
        System.out.println("2. Удалить задачу");
        System.out.println("3. Получить задачу на указанный день");
        System.out.println("0. Выход");
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            deleteTask(scanner);
                            break;
                        case 3:
                                checkTasks(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }
}