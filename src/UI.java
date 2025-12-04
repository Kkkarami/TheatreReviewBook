import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Клас графічного інтерфейсу, для взаємодії з користувачем
 */
public class UI {
    private static User currentUser;
    private static final Service service = new Service();
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Запускає стартове меню для авторизації або реєстрації
     */
    public static void start() throws SQLException {
        System.out.println("1.Авторизація\n2.Реєстрація");
        System.out.print("Введіть ваш вибір: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) loginMenu();
        if (choice == 2) registrationMenu();
    }

    /**
     * Меню авторизації
     */
    public static void loginMenu() throws SQLException {
        System.out.print("Введіть ваш логін: ");
        String name = sc.nextLine();
        System.out.print("Введіть ваш пароль: ");
        String password = sc.nextLine();

        currentUser = service.login(name, password);
        if (currentUser != null) {
            System.out.println("Вітаємо, " + name);
            mainMenu();
        } else {
            System.out.println("Неправильний логін або пароль!");
        }
    }

    /**
     * Меню реєстрації
     */
    public static void registrationMenu() throws SQLException {
        System.out.print("Введіть ваш логін: ");
        String name = sc.nextLine();
        System.out.print("Введіть ваш пароль: ");
        String password = sc.nextLine();
        System.out.print("Підтвердіть пароль: ");
        String confirmPassword = sc.nextLine();

        if (!password.equals(confirmPassword)) {
            System.out.println("Паролі різні!");
            return;
        }

        System.out.print("Введіть ваш email: ");
        String email = sc.nextLine();
        System.out.print("Введіть ваш телефон: ");
        String phone = sc.nextLine();
        System.out.print("Введіть ваш URL (особиста сторінка): ");
        String url = sc.nextLine();

        List<String> errors = new ArrayList<>();
        errors.addAll(Validator.validateLogin(name));
        errors.addAll(Validator.validatePassword(password));
        errors.addAll(Validator.validateEmail(email));
        errors.addAll(Validator.validateMobile(phone));
        errors.addAll(Validator.validateUrl(url));

        if (!errors.isEmpty()) {
            System.out.println("Помилки при введенні даних:");
            for (String e : errors) {
                System.out.println("- " + e);
            }
            start();
        }

        service.register(name, password, email, url, phone);
        currentUser = service.login(name, password);
        System.out.println("Ваш акаунт успішно зареєстрований!");
        mainMenu();
    }

    /**
     * Головне меню програми
     */
    public static void mainMenu() throws SQLException {
        System.out.println("1.Написати відгук\n2.Всі відгуки");
        System.out.print("Введіть ваш вибір: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            writeReviewMenu();
        } else if (choice == 2) {
            showAllReviews();
        }
    }

    private static void writeReviewMenu() throws SQLException {
        List<Performance> performances = DBManager.loadPerformance();
        System.out.println("Список вистав:");
        for (Performance p : performances) {
            System.out.println("ID: " + p.getId() + " | Назва: " + p.getName() + " | Опис: " + p.getDescription());
        }

        System.out.print("Введіть ID вистави для відгуку: ");
        int choiceId = sc.nextInt();
        sc.nextLine();

        Performance selected = performances.stream()
                .filter(p -> p.getId() == choiceId)
                .findFirst()
                .orElse(null);

        if (selected == null) {
            System.out.println("Вистава не знайдена!");
            return;
        }

        System.out.print("Введіть ваш відгук: ");
        String reviewText = sc.nextLine();
        String today = LocalDate.now().toString();

        service.createReview(currentUser.getId(), selected.getId(), reviewText, today);
        System.out.println("Ваш відгук успішно додано!");
        mainMenu();
    }

    private static void showAllReviews() throws SQLException {
        List<Review> reviews = DBManager.loadReviews();
        List<User> users = DBManager.loadUsers();
        List<Performance> performances = DBManager.loadPerformance();

        System.out.println("Всі відгуки:");
        for (Review r : reviews) {
            String userName = users.stream()
                    .filter(u -> u.getId() == r.getUserId())
                    .map(User::getUserName)
                    .findFirst().orElse("");
            String perfName = performances.stream()
                    .filter(p -> p.getId() == r.getPerformanceId())
                    .map(Performance::getName)
                    .findFirst().orElse("");

            System.out.println("ID: " + r.getId()
                    + " | Користувач: " + userName
                    + " | Вистава: " + perfName
                    + " | Відгук: " + r.getText()
                    + " | Дата: " + r.getDate());
        }
        mainMenu();
    }
}
