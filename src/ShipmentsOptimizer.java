import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ShipmentsOptimizer {
    private Connection connection;

    public ShipmentsOptimizer() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/warehouse_system", "username", "password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void optimizeShipments() {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE shipments SET status = 'optimized' WHERE status = 'pending'");
            statement.executeUpdate();
            connection.commit();
            System.out.println("Отгрузки оптимизированы.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
