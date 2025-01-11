import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseAccess {
    private Connection connection;

    public DatabaseAccess() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/warehouse_system", "username", "password");
        } catch (Exception e) {
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
}
