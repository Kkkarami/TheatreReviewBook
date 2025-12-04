package DAO;

import Entities.User;

import java.util.List;

/**
 * DAO інтерфейс для користувачів
 */
public interface UserDao {
    User findById(int id);
    User findByName(String name);
    List<User> findAll();
    void save(User user);
}


