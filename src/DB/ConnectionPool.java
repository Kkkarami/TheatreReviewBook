package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final ConnectionPool INSTANCE = new ConnectionPool();

    private final BlockingQueue<Connection> pool;
    private final String url = "jdbc:h2:~/testdb";
    private final String user = "sa";
    private final String password = "";
    private final int MAX_CONNECTIONS = 5;

    private ConnectionPool() {
        pool = new ArrayBlockingQueue<>(MAX_CONNECTIONS);
        try {
            for (int i = 0; i < MAX_CONNECTIONS; i++) {
                pool.add(createConnection());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating initial connections", e);
        }
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Отримати підключення з пулу.
     * @return Connection
     */
    public Connection getConnection() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while getting connection", e);
        }
    }

    /**
     * Повернути підключення в пул.
     * @param connection
     */
    public void releaseConnection(Connection connection) {
        if (connection == null) return;
        try {
            // rollback any uncommitted work to keep connection clean
            if (!connection.getAutoCommit()) connection.rollback();
        } catch (SQLException ignored) {}

        try {
            pool.put(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            try { connection.close(); } catch (SQLException ignored) {}
        }
    }

    /**
     * Закрити всі підключення (для завершення програми).
     */
    public void shutdown() {
        while (!pool.isEmpty()) {
            try {
                Connection c = pool.poll();
                if (c != null) c.close();
            } catch (SQLException ignored) {}
        }
    }
}
