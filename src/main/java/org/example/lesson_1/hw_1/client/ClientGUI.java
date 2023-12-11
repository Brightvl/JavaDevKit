package org.example.lesson_1.hw_1.client;


import org.example.lesson_1.hw_1.client.widgets.MessageDisplayWindowPanel;
import org.example.lesson_1.hw_1.client.widgets.SendMessagePanel;
import org.example.lesson_1.hw_1.client.widgets.VerificationPanel;
import org.example.lesson_1.hw_1.server.Server;
import org.example.lesson_1.hw_1.server.User;

import javax.swing.*;
import java.awt.*;

public class ClientGUI extends JFrame {

    private User user;

    // размеры окна
    private static final int WIDTH = 400;
    private static final int HEIGHT = 507;

    //пройдена ли авторизация
    private boolean isAuthorized;

    //widgets
    private VerificationPanel verificationPanel;

    private MessageDisplayWindowPanel messageDisplayWindowPanel;
    private SendMessagePanel sendMessagePanel;

    //
    private JPanel mainPanel;

    // клиент знает про сервер
    private Server server;


    /**
     * Конструктор
     * @param server
     */
    private ClientGUI(Server server) {
        this.server = server;
        this.isAuthorized = false;


        this.messageDisplayWindowPanel = new MessageDisplayWindowPanel(this);

        // задаем параметры окна
        paramWindow();
        //выводит панель верификации
        createVerificationPanel();
    }


    /**
     * Создает поле для ввода верификационных параметров пользователя
     */
    private void createVerificationPanel() {
        this.mainPanel = new JPanel(new BorderLayout(2, 1));
        this.verificationPanel = new VerificationPanel(this);
        this.mainPanel.add(verificationPanel, BorderLayout.NORTH);


        add(mainPanel);
    }

    /**
     * Создание окна чата и ввода сообщения
     */
    private void createChat() {
        this.mainPanel.removeAll(); // Очищаем текущие компоненты
        this.mainPanel.add(messageDisplayWindowPanel, BorderLayout.CENTER);
        add(mainPanel);

        this.sendMessagePanel = new SendMessagePanel(this);
        add(sendMessagePanel, BorderLayout.SOUTH);

        revalidate(); // Обновляем компоненты окна
        repaint();
    }


    /**
     * Фабричный метод для создания объекта и отображения окна
     */
    public static ClientGUI createClient(Server server, Point pointWindow, int indentX, int indentY) {
        // Создаем новое окно клиента
        ClientGUI clientGUI = new ClientGUI(server);

        // сдвиг окна клиента относительно координат
        int x = (int) pointWindow.getX();
        int y = (int) pointWindow.getY();
        clientGUI.setLocation(x + indentX, y + indentY);

        clientGUI.setVisible(true);

        server.addClient(clientGUI);
        return clientGUI;
    }

    /**
     * Задает параметры клиентского окна
     */
    private void paramWindow() {
        setTitle("General chat"); // название окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false); // запрет на растягивание окна
    }


    /**
     * Проверяет верификацию введенных данных пользователя у сервера
     * @param ip Ip адрес
     * @param port порт
     * @param login логин
     * @param password пароль
     * @return true если авторизация прошла успешно
     */
    public boolean checkVerification(String ip, String port, String login, String password) {
        if (server.isServerWorking()) {
            int verificationLog = server.checkVerification(ip, port, login, password);
            switch (verificationLog) {
                case 1 -> {
                    JOptionPane.showMessageDialog(this, "Ip адрес не найден");
                    return false;
                }
                case 2 -> {
                    JOptionPane.showMessageDialog(this, "Неверно указанный порт");
                    return false;
                }
                case 3 -> {
                    JOptionPane.showMessageDialog(this, "Для авторизации введите логин и пароль");
                    return false;
                }
                case 4 -> {
                    JOptionPane.showMessageDialog(this, "Не верный логин или пароль");
                    return false;
                }
                default -> {
                    this.user = server.getUser();
                    createChat();
                    return true;
                }
            }
        } else JOptionPane.showMessageDialog(this, "Сервер не запущен");
        return false;
    }

    /**
     * Сохраняет сообщение на сервере
     * @param message сообщение
     */
    public void appendUserMessageToServer(String message) {
        server.appendUserMessageToServer(user, message);
        appendSentMessage(message);
    }

    /**
     * Добавляет сообщение полученное от пользователя в поле исходящие
     *
     * @param message сообщение
     */
    public void appendSentMessage(String message) {
        String sendMessage = user.getLogin() + ": "+ message;
        this.messageDisplayWindowPanel.appendSentMessage(sendMessage);
    }


    /**
     * Добавляет сообщение полученное от пользователя в поле входящие
     *
     * @param message входящее сообщение
     */
    public void appendReceiveMessage(User user, String message) {
        this.messageDisplayWindowPanel.appendReceiveMessage(user.getLogin() + ": " + message);
    }



    //region геттеры и сеттеры

    public String getIp() {
        return server.getIpAddress();
    }
    //endregion
}
