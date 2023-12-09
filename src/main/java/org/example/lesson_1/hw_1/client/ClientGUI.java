package org.example.lesson_1.hw_1.client;


import org.example.lesson_1.hw_1.client.widgets.ClientGUILoginPanel;
import org.example.lesson_1.hw_1.client.widgets.MessageDisplayWindowPanel;
import org.example.lesson_1.hw_1.client.widgets.SendMessagePanel;
import org.example.lesson_1.hw_1.server.Server;

import javax.swing.*;
import java.awt.*;

public class ClientGUI extends JFrame {

    // размеры окна
    private static final int WIDTH = 400;
    private static final int HEIGHT = 507;

    //пройдена ли авторизация
    private boolean isAuthorized;

    //widgets
    private ClientGUILoginPanel clientGUILogin;
    private MessageDisplayWindowPanel messageDisplayWindowPanel;
    private SendMessagePanel sendMessagePanel;

    //
    private JPanel mainPanel;
    private Server server;


    private ClientGUI(Server server) {
        this.server = server;
        this.isAuthorized = false;
        paramWindow();

        mainPanel = new JPanel(new BorderLayout(2, 1));

        this.clientGUILogin = new ClientGUILoginPanel(this);
        this.mainPanel.add(clientGUILogin, BorderLayout.NORTH);

        this.messageDisplayWindowPanel = new MessageDisplayWindowPanel();
        this.mainPanel.add(messageDisplayWindowPanel, BorderLayout.CENTER);


        add(mainPanel);


        //todo пока не залогинились поставить запрет
        this.sendMessagePanel = new SendMessagePanel(this);
        add(sendMessagePanel, BorderLayout.SOUTH);
    }


    /**
     * Фабричный метод для создания объекта и отображения окна
     */
    public static ClientGUI createClient(Server server, Point pointWindow, int indentX, int indentY) {
        ClientGUI clientWindow = new ClientGUI(server);
        int x = (int) pointWindow.getX();
        int y = (int) pointWindow.getY();
        clientWindow.setLocation(x + indentX, y + indentY);

        clientWindow.setVisible(true);

        server.addClient(clientWindow);
        return clientWindow;
    }

    /**
     * Задает параметры окна
     */
    private void paramWindow() {


        //region параметры окна

        setTitle("General chat"); // название окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false); // запрет на растягивание окна

        //endregion
    }


    public boolean checkVerification(String ip, String port, String login, String password) {
        return server.checkVerification(ip, port, login, password);
    }


    public void appendSentMessage(String message) {
        this.messageDisplayWindowPanel.appendSentMessage(message);
        server.clientSendMessage(message);

    }

    public void appendReceiveMessage(String message) {
        this.messageDisplayWindowPanel.appendReceiveMessage(message);
    }

}
