import java.sql.*;

public class GoodsBalanceManager {
    private Connection connection;

    public GoodsBalanceManager() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "1111");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getGoodsBalance(String goodName) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT quantity FROM product WHERE name = ?");
            statement.setString(1, goodName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int quantity = resultSet.getInt("quantity");
                System.out.println("Остаток товара: " + quantity);
            } else {
                System.out.println("Товар не найден.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
