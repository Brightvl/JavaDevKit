package org.example.hw_server.client.ui;


import org.example.hw_server.client.ui.widgets.MessageDisplayWindowPanel;
import org.example.hw_server.client.ui.widgets.SendMessagePanel;
import org.example.hw_server.client.ui.widgets.VerificationPanel;
import org.example.hw_server.server.Server;
import org.example.hw_server.server.User;

import javax.swing.*;
import java.awt.*;

public class ClientGUI extends JFrame implements View{


    private User user;

    // размеры окна
    private static final int WIDTH = 400;
    private static final int HEIGHT = 507;

    //пройдена ли авторизация
    private boolean isAuthorized;

    private final MessageDisplayWindowPanel messageDisplayWindowPanel;

    private final JPanel mainPanel;

    // клиент знает про сервер
    private final Server server;


    /**
     * Конструктор
     * @param server сервер
     */
    private ClientGUI(Server server) {
        this.server = server;
        this.isAuthorized = false;

        this.mainPanel = new JPanel(new BorderLayout(2, 1));
        this.messageDisplayWindowPanel = new MessageDisplayWindowPanel(this);

        // задаем параметры окна
        paramWindow();
        //выводит панель верификации
        clientAutonomization();
    }


    /**
     * Логика GUI
     */
    public void clientAutonomization() {
        if (server.isServerWorking()) {
            if (!isAuthorized) {
                createVerificationPanel();
            } else createChat();

        } else {
            if (isAuthorized) {
                showNotification("Сервер не отвечает");
                this.isAuthorized = false;
            }
            createVerificationPanel();
        }
    }


    /**
     * Создает поле для ввода верификационных параметров пользователя
     */
    private void createVerificationPanel() {
        this.mainPanel.removeAll(); // Очищаем текущие компоненты
        //widgets
        VerificationPanel verificationPanel = new VerificationPanel(this);
        this.mainPanel.add(verificationPanel, BorderLayout.NORTH);

        add(mainPanel);
        revalidate(); // Обновляем компоненты окна
        repaint();
    }

    /**
     * Создание окна чата и ввода сообщения
     */
    private void createChat() {
        this.mainPanel.removeAll(); // Очищаем текущие компоненты
        this.mainPanel.add(messageDisplayWindowPanel, BorderLayout.CENTER);

        add(mainPanel);

        SendMessagePanel sendMessagePanel = new SendMessagePanel(this);
        add(sendMessagePanel, BorderLayout.SOUTH);

        revalidate(); // Обновляем компоненты окна
        repaint();
    }


    /**
     * Фабричный метод для создания объекта и отображения окна
     */
    public static void createClient(Server server, Point pointWindow, int indentX, int indentY) {
        // Создаем новое окно клиента
        ClientGUI clientGUI = new ClientGUI(server);

        // сдвиг окна клиента относительно координат
        int x = (int) pointWindow.getX();
        int y = (int) pointWindow.getY();
        clientGUI.setLocation(x + indentX, y + indentY);

        clientGUI.setVisible(true);

        server.addClient(clientGUI);
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
     *
     * @param ip       Ip адрес
     * @param port     порт
     * @param login    логин
     * @param password пароль
     */
    public void checkVerification(String ip, String port, String login, String password) {
        if (server.isServerWorking()) {
            int verificationLog = server.checkVerification(ip, port, login, password);
            switch (verificationLog) {
                case 1 -> {
                    showNotification("Ip адрес не найден");
                }
                case 2 -> {
                    showNotification("Неверно указанный порт");
                }
                case 3 -> {
                    showNotification("Для авторизации введите логин и пароль");
                }
                case 4 -> {
                    showNotification("Не верный логин или пароль");
                }
                default -> {
                    this.user = server.findByLogin(login);
                    this.isAuthorized = true;
                    createChat();
                }
            }
        } else showNotification("Сервер не запущен");
    }

    /**
     * Сохраняет сообщение на сервере
     *

     * @param message сообщение
     */
    public void appendUserMessage(String message) {
        server.appendUserMessage(this.user,message);
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
    public void appendReceiveMessage(String message) {
        this.messageDisplayWindowPanel.appendReceiveMessage(message);
    }

    public void showNotification(String message) {
        JOptionPane.showMessageDialog(this, message);
    }



    //region геттеры и сеттеры
    public String getIp() {
        return Server.getIpAddress();
    }

    public String getReceiveText() {
        return server.readMessage();
    }
    //endregion
}
