import DAO.*;

public class DaoFactory {
    public static UserDao getUserDao() {
        return UserDaoImpl.getInstance();
    }

    public static PerformanceDao getPerformanceDao() {
        return PerformanceDaoImpl.getInstance();
    }

    public static ReviewDao getReviewDao() {
        return ReviewDaoImpl.getInstance();
    }
}
