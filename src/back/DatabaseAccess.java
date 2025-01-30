package back;

import java.sql.*;

public class DatabaseAccess {
    private Connection connection;

    public DatabaseAccess() {

            try {
                Class.forName("org.postgresql.Driver"); // Загрузка драйвера
                String url = "jdbc:postgresql://localhost:5432/postgres";
                String username = "postgres";
                String password = "1111";
                this.connection = DriverManager.getConnection(url, username, password);
                System.out.println("Подключение успешно установлено.");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    public void executeUpdate(String sql, Object... params) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 1; i <= params.length; i++) {
                if (params[i - 1] != null) {
                    statement.setObject(i, params[i - 1]);
                }
            }
            statement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String sql, Object... params) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 1; i <= params.length; i++) {
                if (params[i - 1] != null) {
                    statement.setObject(i, params[i - 1]);
                }
            }
            return statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public Connection getConnection() {
        return connection;
    }
}
