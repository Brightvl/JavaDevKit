package org.example.lesson_1.hw_1.server;

import org.example.lesson_1.hw_1.client.ClientGUI;
import org.example.lesson_1.hw_1.server.ui.ServerGUI;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Server {

    // адрес сервера
    private static final String ipAddress = "192.168.168.255";
    private static final String port = "8.8.8.8";

    // состояние
    private boolean isServerWorking;

    // хранилища
    private final List<User> userList;
    private final List<ClientGUI> clientGUIs;


    private final File LOG_FILE_PATH = new File("src/main/java/org/example/lesson_1/hw_1/data/filename.txt");

    // сообщение из чата
    private User user;
    private String chatMessage;

    //окно клиента
    private ServerGUI serverGUI;


    /**
     * Конструктор
     */
    public Server() {
        this.isServerWorking = false;
        this.userList = new ArrayList<>();
        this.clientGUIs = new ArrayList<>();

        serverGUI = new ServerGUI(this);
        serverGUI.runProgram();
    }


    /**
     * Добавляет пользователя в хранилище сервера
     *
     * @param user пользователь
     */
    public void addUser(User user) {
        userList.add(user);
    }


    /**
     * Проерка верификации пользователя
     *
     * @param ipAddress ip адрес
     * @param port      порт
     * @param login     логин
     * @param password  пароль
     * @return значение ошибки
     */
    public int checkVerification(String ipAddress, String port, String login, String password) {
        if (!ipAddress.equals(Server.ipAddress)) return 1;
        if (!port.equals(Server.port)) return 2;

        this.user = findByLogin(login);
        if (user == null) return 3;
        if (!(user.getLogin().equalsIgnoreCase(login) &&
                user.getPassword().equalsIgnoreCase(password))) return 4;
        appendMessageInLogServerGUI("User verification " + login + " was successful");
        return 0;
    }

    /**
     * Поиск по логину в архиве пользователей сервера
     * @param login логин
     * @return
     */
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

    /**
     * Добавляет отправленное сообщение в логи сервера
     * @param user пользователь
     * @param message сообщение
     */
    private void appendMessageToServerLog(User user, String message) {
        serverGUI.appendUserMessageToServerLog(user, message);
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


    public void addClient(ClientGUI clientGUI) {
        clientGUIs.add(clientGUI);
    }

    public ServerGUI getServerGUI() {
        return serverGUI;
    }



    public User getUser() {
        return user;
    }

    public static String getIpAddress() {
        return ipAddress;
    }

    public static String getPort() {
        return port;
    }

    /**
     * @return Возвращает bool значение состояния сервера
     */
    public boolean isServerWorking() {
        return isServerWorking;
    }

    /**
     * Меняет значение состояния сервера
     *
     * @param serverWorking boolean
     */
    public void setServerWorking(boolean serverWorking) {
        isServerWorking = serverWorking;
    }
}
