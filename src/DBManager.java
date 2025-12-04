import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 * Клас обробки роботи з таблицями користувачів, вистав, відгуків
 * Читання та запис БД
 */
public class DBManager {

    /**
     * Зчитує всіх користувачів з таблиці Users
     * @return список користувачів
     * @throws SQLException
     */
    public static List<User> loadUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("userName"),
                        rs.getString("hashPassword"),
                        rs.getString("email"),
                        rs.getString("url"),
                        rs.getString("phoneNumber")
                ));
            }
        }
        return users;
    }

    /**
     * Зчитує всі вистави з таблиці Performances
     * @return список вистав
     * @throws SQLException
     */
    public static List<Performance> loadPerformance() throws SQLException {
        List<Performance> performances = new ArrayList<>();
        String sql = "SELECT * FROM Performances";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                performances.add(new Performance(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                ));
            }
        }
        return performances;
    }

    /**
     * Зчитує всі відгуки з таблиці Reviews
     * @return список відгуків
     * @throws SQLException
     */
    public static List<Review> loadReviews() throws SQLException {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM Reviews";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                reviews.add(new Review(
                        rs.getInt("id"),
                        rs.getInt("userId"),
                        rs.getInt("performanceId"),
                        rs.getString("text"),
                        rs.getString("date")
                ));
            }
        }
        return reviews;
    }

    /**
     * Додає нового користувача у таблицю Users
     * @param user користувач для додавання
     * @throws SQLException
     */
    public static void addUser(User user) throws SQLException {
        String sql = "INSERT INTO Users (userName, hashPassword, email, url, phoneNumber) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getHashPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getUrl());
            ps.setString(5, user.getPhoneNumber());
            ps.executeUpdate();
        }
    }

    /**
     * Додає новий відгук у таблицю Reviews
     * @param review відгук для додавання
     * @throws SQLException
     */
    public static void addReview(Review review) throws SQLException {
        String sql = "INSERT INTO Reviews (userId, performanceId, text, date) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, review.getUserId());
            ps.setInt(2, review.getPerformanceId());
            ps.setString(3, review.getText());
            ps.setString(4, review.getDate());
            ps.executeUpdate();
        }
    }
}
