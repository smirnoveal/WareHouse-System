import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InventoryManager {
    private Connection connection;

    public InventoryManager() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/warehouse_system", "username", "password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void performInventory() {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE inventory SET status = 'in_progress' WHERE status = 'pending'");
            statement.executeUpdate();
            connection.commit();
            System.out.println("Инвентаризация начата.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
