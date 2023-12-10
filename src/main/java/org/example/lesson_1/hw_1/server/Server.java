package org.example.lesson_1.hw_1.server;

import org.example.lesson_1.hw_1.client.ClientGUI;
import org.example.lesson_1.hw_1.server.ui.ServerGUI;

import java.util.ArrayList;
import java.util.List;

public class Server {

    private static String ipAddress = "1111";
    private static String port = "8888";

    private boolean isServerWorking;

    private List<User> userList;
    private List<ClientGUI> clientGUIs;

    public String chatMessage;

    private ServerGUI serverGUI;
    private User user;

    public Server() {
        this.isServerWorking = false;
        this.userList = new ArrayList<>();
        this.clientGUIs = new ArrayList<>();

        serverGUI = new ServerGUI(this);
        serverGUI.runProgram();
    }


    public void addUser(User user) {
        userList.add(user);
    }


    public boolean isServerWorking() {
        return isServerWorking;
    }

    public void setServerWorking(boolean serverWorking) {
        isServerWorking = serverWorking;
    }

    public int checkVerification(String ipAddress, String port, String login, String password) {
        if (!ipAddress.equals(Server.ipAddress)) return 1;
        if (!port.equals(Server.port)) return 2;

        this.user = findByLogin(login);

        if (user == null) {
            return 3;
        }
        if (!(user.getLogin().equalsIgnoreCase(login) && user.getPassword().equalsIgnoreCase(password))) {
            return 4;
        }
        appendMessageInLogServerGUI("User verification " + login + " was successful");
        return 0;
    }

    private User findByLogin(String login) {
        for (User user : userList
        ) {
            if (user.getLogin().equalsIgnoreCase(login)) {
                return user;
            }
        }
        return null;
    }


    /**
     * Получает сообщение отправленное клиентом и добавляет его в общий чат
     *
     * @param message отправленное клиентом сообщение
     */
    public void appendClientSentMessageToGeneralChat(User user, String message) {
        for (ClientGUI clientGUI : clientGUIs
        ) {
            clientGUI.appendReceiveMessage(user, message);
        }
    }

    private void appendMessageToServerLog(User user, String message) {
        serverGUI.appendMessageToServerLog(user, message);
    }


    public ServerGUI getServerGUI() {
        return serverGUI;
    }


    public void addClient(ClientGUI clientGUI) {
        clientGUIs.add(clientGUI);
    }

    public void appendMessageInLogServerGUI(String message) {
        serverGUI.getServerMessage(message);
    }

    public void appendUserMessageToServer(User user, String message) {
        this.chatMessage = message;
        this.user = user;
        appendClientSentMessageToGeneralChat(user, chatMessage);
        appendMessageToServerLog(user, message);
    }

    public User getUser() {
        return user;
    }
}
