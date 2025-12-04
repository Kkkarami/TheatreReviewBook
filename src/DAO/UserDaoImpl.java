package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Entities.User;
import DB.ConnectionPool;
import Exceptions.*;

public class UserDaoImpl implements UserDao{
    private static final UserDaoImpl INSTANCE = new UserDaoImpl();
    private final ConnectionPool pool = ConnectionPool.getInstance();

    private UserDaoImpl() {}

    public static UserDaoImpl getInstance() { return INSTANCE; }

    @Override
    public User findById(int id) {
        Connection c = pool.getConnection();
        try {
            String sql = "SELECT * FROM Users WHERE id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new EntityNotFoundException("User id=" + id + " not found");
            }
            User u = User.builder()
                    .id(rs.getInt("id"))
                    .userName(rs.getString("userName"))
                    .hashPassword(rs.getString("hashPassword"))
                    .email(rs.getString("email"))
                    .url(rs.getString("url"))
                    .phoneNumber(rs.getString("phoneNumber"))
                    .build();
            rs.close();
            ps.close();
            return u;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(c);
        }
    }

    @Override
    public User findByName(String name) {
        Connection c = pool.getConnection();
        try {
            String sql = "SELECT * FROM Users WHERE userName = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            }
            User u = User.builder()
                    .id(rs.getInt("id"))
                    .userName(rs.getString("userName"))
                    .hashPassword(rs.getString("hashPassword"))
                    .email(rs.getString("email"))
                    .url(rs.getString("url"))
                    .phoneNumber(rs.getString("phoneNumber"))
                    .build();
            rs.close();
            ps.close();
            return u;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(c);
        }
    }

    @Override
    public List<User> findAll() {
        Connection c = pool.getConnection();
        List<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Users";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                users.add(User.builder()
                        .id(rs.getInt("id"))
                        .userName(rs.getString("userName"))
                        .hashPassword(rs.getString("hashPassword"))
                        .email(rs.getString("email"))
                        .url(rs.getString("url"))
                        .phoneNumber(rs.getString("phoneNumber"))
                        .build());
            }
            rs.close();
            st.close();
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(c);
        }
    }

    @Override
    public void save(User user) {
        // Перевірка дубля по userName
        if (findByName(user.getUserName()) != null) {
            throw new DuplicateEntityException("User with name '" + user.getUserName() + "' already exists");
        }

        Connection c = pool.getConnection();
        try {
            String sql = "INSERT INTO Users (userName, hashPassword, email, url, phoneNumber) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getHashPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getUrl());
            ps.setString(5, user.getPhoneNumber());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(c);
        }
    }
}
