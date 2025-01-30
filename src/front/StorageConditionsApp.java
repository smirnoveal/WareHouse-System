package front;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StorageConditionsApp extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField temperatureField;
    private JTextField humidityField;

    public StorageConditionsApp() {
        setTitle("Авторизация и условия хранения");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Панель для авторизации
        JPanel authPanel = new JPanel(new GridLayout(3, 2));
        authPanel.add(new JLabel("Имя пользователя:"));
        usernameField = new JTextField();
        authPanel.add(usernameField);
        authPanel.add(new JLabel("Пароль:"));
        passwordField = new JPasswordField();
        authPanel.add(passwordField);
        JButton loginButton = new JButton("Войти");
        authPanel.add(loginButton);

        // Панель для ввода условий хранения
        JPanel storagePanel = new JPanel(new GridLayout(3, 2));
        storagePanel.add(new JLabel("Температура воздуха:"));
        temperatureField = new JTextField();
        storagePanel.add(temperatureField);
        storagePanel.add(new JLabel("Влажность:"));
        humidityField = new JTextField();
        storagePanel.add(humidityField);
        JButton submitButton = new JButton("Отправить");
        storagePanel.add(submitButton);

        // Изначально панель с условиями хранения скрыта
        storagePanel.setVisible(false);

        // Добавляем панели на основное окно
        setLayout(new BorderLayout());
        add(authPanel, BorderLayout.NORTH);
        add(storagePanel, BorderLayout.CENTER);

        // Обработчик кнопки входа
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Простая проверка авторизации
                if ("admin".equals(username) && "password".equals(password)) {
                    JOptionPane.showMessageDialog(StorageConditionsApp.this, "Авторизация успешна!");
                    authPanel.setVisible(false);
                    storagePanel.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(StorageConditionsApp.this, "Неверное имя пользователя или пароль!");
                }
            }
        });

        // Обработчик кнопки отправки данных
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double temperature = Double.parseDouble(temperatureField.getText());
                    double humidity = Double.parseDouble(humidityField.getText());

                    // Здесь можно добавить логику для обработки данных
                    JOptionPane.showMessageDialog(StorageConditionsApp.this,
                            "Данные приняты!\nТемпература: " + temperature + "°C\nВлажность: " + humidity + "%");

                    // Закрываем текущее окно
                    dispose();

                    // Открываем второе окно
                    WarehouseSystemFrame secondWindow = new WarehouseSystemFrame();
                    secondWindow.setVisible(true);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(StorageConditionsApp.this, "Пожалуйста, введите корректные числовые значения!");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StorageConditionsApp().setVisible(true);
            }
        });
    }
}