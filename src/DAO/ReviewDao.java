package DAO;

import Entities.Review;

import java.util.List;
/**
 * DAO інтерфейс для відгуків
 */
public interface ReviewDao {
    List<Review> findAll();
    void save(Review review);
}
