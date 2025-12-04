package DAO;

import DB.ConnectionPool;
import Entities.Performance;
import Exceptions.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PerformanceDaoImpl implements PerformanceDao{
    private static final PerformanceDaoImpl INSTANCE = new PerformanceDaoImpl();
    private final ConnectionPool pool = ConnectionPool.getInstance();

    private PerformanceDaoImpl() {}

    public static PerformanceDaoImpl getInstance() { return INSTANCE; }

    @Override
    public Performance findById(int id) {
        Connection c = pool.getConnection();
        try {
            String sql = "SELECT * FROM Performances WHERE id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) throw new EntityNotFoundException("Performance id=" + id + " not found");
            Performance p = Performance.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .build();
            rs.close();
            ps.close();
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(c);
        }
    }

    @Override
    public List<Performance> findAll() {
        Connection c = pool.getConnection();
        List<Performance> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Performances";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(Performance.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .description(rs.getString("description"))
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
}
