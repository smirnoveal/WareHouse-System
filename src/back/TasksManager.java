package back;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TasksManager {
    private Connection connection;

    public TasksManager() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1111");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTask(String taskName) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO tasks (name) VALUES (?)");
            statement.setString(1, taskName);
            statement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void assignTask(String taskName, String assignee) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE tasks SET assignee = ? WHERE name = ?");
            statement.setString(1, assignee);
            statement.setString(2, taskName);
            statement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
