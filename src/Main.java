import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static void inputTask(Scanner scanner) throws ParseException {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.next();
        System.out.print("Введите описание задачу: ");
        String taskDesc = scanner.next();
        System.out.print("Укажите дату выполнения(в формате день.месяц.год): ");
        String stringDate = scanner.next();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        date = format.parse(stringDate);
        System.out.print("Укажите время выполнения(час в 24-часовом формате): ");
        date.setHours(scanner.nextInt());
        System.out.print("Укажите тип задачи(1 - если личная, 2 - если рабочая, по умолчанию выставляется личная): ");
        int taskType = scanner.nextInt();
        System.out.print("Укажите повторяемость задачи(0 - неповторяемая, 1 - ежедневная, 2 - еженедельная, " +
                "3 - ежемесячная, 4 - ежегодная): ");
        int taskRepeatability = scanner.nextInt();
        switch (taskRepeatability) {
            case 0:
                Task.addToList(new Task<>(taskName, taskDesc, taskType, date));
                break;
            case 1:
                Task.addToList(new Task<Task.DailyRepeat>(taskName, taskDesc, taskType, date));
                break;
            case 2:
                Task.addToList(new Task<Task.WeeklyRepeat>(taskName, taskDesc, taskType, date));
                break;
            case 3:
                Task.addToList(new Task<Task.MonthlyRepeat>(taskName, taskDesc, taskType, date));
                break;
            case 4:
                Task.addToList(new Task<Task.YearlyRepeat>(taskName, taskDesc, taskType, date));
                break;
        }
        System.out.print("Задача " + taskName + " добавлена!");
    }

    public static void deleteTask(Scanner scanner) {
        System.out.print("Введите ID задачи: ");
        Task.deleteFromList(scanner.nextInt());
    }

    public static void checkTasks(Scanner scanner) throws ParseException{
        System.out.print("Введите дату: ");
        String stringDate = scanner.next();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        date = format.parse(stringDate);
        Task.checkTasks(date);
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
                            try {
                                inputTask(scanner);
                                break;
                            } catch (ParseException e) {
                                System.out.println("Не удалось добавить задачу");
                            }
                        case 2:
                            deleteTask(scanner);
                            break;
                        case 3:
                            try {
                                checkTasks(scanner);
                            } catch (ParseException e) {
                                System.out.println("Не удалось получить задачи на указанный день");
                            }
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