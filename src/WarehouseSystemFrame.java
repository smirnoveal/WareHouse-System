import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class WarehouseSystemFrame extends JFrame {
    private JButton addGoodButton;
    private JButton deleteGoodButton;
    private JButton issueGoodButton;
    private JButton addTaskButton;
    private JButton assignTaskButton;
    private JButton getGoodsBalanceButton;
    private JButton optimizeShipmentsButton;
    private JButton performInventoryButton;
    private JTextArea goodsListArea;

    private Good goodsManager;
    private GoodsBalanceManager goodsBalanceManager;
    private TasksManager tasksManager;
    private DatabaseAccess databaseAccess;

    public WarehouseSystemFrame() {
        super("Warehouse System");
        setLayout(new FlowLayout());

        // Инициализация компонентов
        addGoodButton = new JButton("Добавить товар");
        deleteGoodButton = new JButton("Удалить товар");
        issueGoodButton = new JButton("Выдать товар");
        addTaskButton = new JButton("Добавить задачу");
        assignTaskButton = new JButton("Назначить задачу");
        getGoodsBalanceButton = new JButton("Получить остаток товаров");
        goodsListArea = new JTextArea(20, 50);
        optimizeShipmentsButton = new JButton("Оптимизировать отгрузки");
        performInventoryButton = new JButton("Провести инвентаризацию");


        // Добавление компонентов на фрейм
        add(addGoodButton);
        add(deleteGoodButton);
        add(issueGoodButton);
        add(addTaskButton);
        add(assignTaskButton);
        add(getGoodsBalanceButton);
        add(optimizeShipmentsButton);
        add(new JScrollPane(goodsListArea));

        // Установка размера фрейма и его отображение
        setSize(1400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        JTextField quantityField;



        // Инициализация менеджеров и доступа к базе данных
        goodsManager = new Good();
        goodsBalanceManager = new GoodsBalanceManager();
        tasksManager = new TasksManager();
        databaseAccess = new DatabaseAccess();

        // Установка слушателей действий
        addGoodActionListener();
        deleteGoodActionListener();
        issueGoodActionListener();
        addTaskActionListener();
        assignTaskActionListener();
        getGoodsBalanceActionListener();
        optimizeShipmentsActionListener();
        performInventoryActionListener();
    }

    private void addGoodActionListener() {
        addGoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String goodName = JOptionPane.showInputDialog("Введите название товара:");
                int quantity = Integer.parseInt(JOptionPane.showInputDialog("Количество:"));
                if (goodName != null) {
                    try {
                        goodsManager.addGood(goodName, quantity);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    JOptionPane.showMessageDialog(null, "Товар добавлен успешно!");
                }
            }
        });
    }

    private void deleteGoodActionListener() {
        deleteGoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String goodName = JOptionPane.showInputDialog("Введите название товара для удаления:");
                if (goodName != null) {
                    goodsManager.deleteGood(goodName);
                    JOptionPane.showMessageDialog(null, "Товар удален успешно!");
                }
            }
        });
    }

    private void issueGoodActionListener() {
        issueGoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String goodName = JOptionPane.showInputDialog("Введите название товара:");
                int quantity = Integer.parseInt(JOptionPane.showInputDialog("Введите количество:"));
                if (goodName != null && quantity > 0) {
                    goodsManager.issueGood(goodName, quantity);
                    JOptionPane.showMessageDialog(null, "Товар выдан успешно!");
                }
            }
        });
    }
    private void addTaskActionListener() {
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskName = JOptionPane.showInputDialog("Введите название задачи:");
                if (taskName != null) {
                    tasksManager.addTask(taskName);
                    JOptionPane.showMessageDialog(null, "Задача добавлена успешно!");
                }
            }
        });
    }
    private void assignTaskActionListener() {
        assignTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskName = JOptionPane.showInputDialog("Введите название задачи:");
                String assignee = JOptionPane.showInputDialog("Введите исполнителя:");
                if (taskName != null && assignee != null) {
                    tasksManager.assignTask(taskName, assignee);
                    JOptionPane.showMessageDialog(null, "Задача назначена успешно!");
                }
            }
        });
    }

    private void getGoodsBalanceActionListener() {
        getGoodsBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String goodName = JOptionPane.showInputDialog("Введите название товара:");
                if (goodName != null) {
                    int quantity = goodsBalanceManager.getGoodsBalance(goodName);
                    if (quantity != 0) {
                        JOptionPane.showMessageDialog(null, "Остаток товара: " + quantity);
                        displayGoodsList();
                    } else {
                        JOptionPane.showMessageDialog(null, "Товар не найден.");
                    }
                }
            }
        });
    }
    private void displayGoodsList() {
        // Вызов метода getGoodsList из GoodsManager и отображение списка товаров
        List<Good> goodsList = goodsManager.getGoodsList();
        StringBuilder output = new StringBuilder();

        for (Good good : goodsList) {
            output.append(good.getName() + ": " + good.getQuantity() + "\n");
        }

        goodsListArea.setText(output.toString());
    }

    private void optimizeShipmentsActionListener() {
        optimizeShipmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShipmentsOptimizer optimizer = new ShipmentsOptimizer();
                optimizer.optimizeShipments();
                JOptionPane.showMessageDialog(null, "Отгрузки оптимизированы успешно!");
            }
        });
    }

    private void performInventoryActionListener() {
        performInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InventoryManager manager = new InventoryManager();
                manager.performInventory();
                JOptionPane.showMessageDialog(null, "Инвентаризация начата успешно!");
            }
        });
    }




}
