package org.example.hw_server.client.ui;


import org.example.hw_server.client.Client;
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



    private final MessageDisplayWindowPanel messageDisplayWindowPanel;

    private final JPanel mainPanel;

    private Client client;


    /**
     * Конструктор
     * @param server сервер
     */
    private ClientGUI(Server server) {
        this.client = new Client(this, server);


        this.mainPanel = new JPanel(new BorderLayout(2, 1));
        this.messageDisplayWindowPanel = new MessageDisplayWindowPanel(this);

        // задаем параметры окна
        settingWindow();
        //выводит панель верификации
        clientAutonomization();
    }

    /**
     * Логика GUI
     */
    public void clientAutonomization() {
        if (client.checkServerStartup()) {
            if (!client.isAuthorized()) {
                createVerificationPanel();
            } else createChatField();

        } else {
            if (client.isAuthorized()) {
                showNotification("Сервер не отвечает");
                client.setAuthorized(false);
            }
            createVerificationPanel();
        }
    }

    /**
     * Задает параметры клиентского окна
     */
    private void settingWindow() {
        setTitle("General chat"); // название окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false); // запрет на растягивание окна
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
    private void createChatField() {
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
    public static void createClientGUI(Server server, Point pointWindow, int indentX, int indentY) {
        // Создаем новое окно клиента
        ClientGUI clientGUI = new ClientGUI(server);

        // сдвиг окна клиента относительно координат
        int x = (int) pointWindow.getX();
        int y = (int) pointWindow.getY();
        clientGUI.setLocation(x + indentX, y + indentY);

        clientGUI.setVisible(true);

    }

    /**
     * Выдает уведомление
     * @param message
     */
    public void showNotification(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void sendMessage(String message) {
        messageDisplayWindowPanel.sendMessage(message);
    }

    public String getMessage() {
        return client.getMessage();
    }

    public void appendMessage(String message) {
        client.appendMessage(message);
    }


    @Override
    public void connectedToServer() {
        clientAutonomization();
    }

    @Override
    public void disconnectedFromServer() {
        clientAutonomization();
    }

    public String getIp() {
        return client.getIp();
    }

    public void verification(String ipAddress, String port, String login, String password) {
        client.checkVerification(ipAddress,port,login,password);
    }




    //endregion
}
