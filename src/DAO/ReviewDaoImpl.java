package DAO;
import DB.ConnectionPool;
import Entities.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao {

    private static final ReviewDaoImpl INSTANCE = new ReviewDaoImpl();
    private final ConnectionPool pool = ConnectionPool.getInstance();

    private ReviewDaoImpl() {}

    public static ReviewDaoImpl getInstance() { return INSTANCE; }

    @Override
    public List<Review> findAll() {
        Connection c = pool.getConnection();
        List<Review> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Reviews";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(Review.builder()
                        .id(rs.getInt("id"))
                        .userId(rs.getInt("userId"))
                        .performanceId(rs.getInt("performanceId"))
                        .text(rs.getString("text"))
                        .date(rs.getString("date"))
                        .build());
            }
            rs.close();
            st.close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(c);
        }
    }

    @Override
    public void save(Review review) {
        Connection c = pool.getConnection();
        try {
            String sql = "INSERT INTO Reviews (userId, performanceId, text, date) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, review.getUserId());
            ps.setInt(2, review.getPerformanceId());
            ps.setString(3, review.getText());
            ps.setString(4, review.getDate());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(c);
        }
    }
}