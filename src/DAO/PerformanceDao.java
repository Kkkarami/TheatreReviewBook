package DAO;

import Entities.Performance;
import java.util.List;

/**
 * DAO інтерфейс для вистав
 */
public interface PerformanceDao {
    Performance findById(int id);
    List<Performance> findAll();
}

