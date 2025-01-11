import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Good {
    private Connection connection;
    private static List<Good> goodsList = new ArrayList<>();
    private static DatabaseAccess databaseAccess;

    public Good() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/warehouse_system", "username", "password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addGood(String goodName, int quantity) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO goods (name, quantity) VALUES (?, ?)");
            statement.setString(1, goodName);
            statement.setInt(2, quantity); // Используем setInt для вставки количества
            statement.executeUpdate();
            connection.commit();
            saveToDatabase();
            System.out.println("Товар добавлен в базу данных: " + goodName + ", количество: " + quantity);
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback(); // Откат изменений при возникновении ошибки
        }
    }

    public void deleteGood(String goodName) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM goods WHERE name = ?");
            statement.setString(1, goodName);
            statement.executeUpdate();
            connection.commit();
            saveToDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void issueGood(String name, int quantity) {
        for (Good good : goodsList) {
            if (good.getName().equals(name)) {
                if (good.getQuantity() >= quantity) {
                    good.setQuantity(good.getQuantity() - quantity);
                    saveToDatabase();
                    System.out.println("Товар выдан: " + name + ", количество: " + quantity);
                    break;
                } else {
                    System.out.println("Недостаточно товаров: " + name);
                    break;
                }
            }
        }
    }

    public List<Good> getGoodsList() {
        return this.goodsList;
    }

        private String name;
        private int quantity;

        public Good(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    private static void saveToDatabase() {
        try (Connection connection = databaseAccess.getConnection();
             Statement statement = connection.createStatement();) {
            for (Good good : goodsList) {
                statement.executeUpdate("INSERT INTO goods (name, quantity) VALUES ('" + good.getName() + "', '" + good.getQuantity() + "')");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }


