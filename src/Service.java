import java.sql.SQLException;
import java.util.List;

/**
 * Клас обробки логіки програми (бізнес-логіка)
 */
public class Service {

    /**
     * Створює відгук
     * @param userId ID користувача
     * @param performanceId ID вистави
     * @param text текст відгуку
     * @param date дата відгуку
     * @throws SQLException
     */
    public void createReview(int userId, int performanceId, String text, String date) throws SQLException {
        Review review = new Review(0, userId, performanceId, text, date); // ID генерує БД автоматично
        DBManager.addReview(review);
    }

    /**
     * Реєструє нового користувача
     * @param userName логін користувача
     * @param password пароль користувача
     * @throws SQLException
     */
    public void register(String userName, String password, String email, String url, String phoneNumber) throws SQLException {
        List<User> users = DBManager.loadUsers();
        for (User u : users) {
            if (u.getUserName().equals(userName)) {
                System.out.println("User already exists");
                return;
            }
        }

        String hashedPassword = Integer.toString(password.hashCode());

        User newUser = new User(0, userName, hashedPassword, email, url, phoneNumber);
        DBManager.addUser(newUser);
    }

    /**
     * Логінить користувача
     * @param userName логін
     * @param password пароль
     * @return користувач або null, якщо не знайдено
     * @throws SQLException
     */
    public User login(String userName, String password) throws SQLException {
        List<User> users = DBManager.loadUsers();
        String hashedPassword = Integer.toString(password.hashCode());

        for (User u : users) {
            if (u.getUserName().equals(userName) && u.getHashPassword().equals(hashedPassword)) {
                return u;
            }
        }
        return null;
    }
}
