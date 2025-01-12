import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Main {
    public static void main(String[] args) {
        //Инициализация главного фрейма системы
        WarehouseSystemFrame frame = new WarehouseSystemFrame();

        // Установка видимого фрейма
        frame.setVisible(true);


    }
}