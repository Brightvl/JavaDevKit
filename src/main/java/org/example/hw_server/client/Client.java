package org.example.hw_server.client;

import com.sun.source.tree.BreakTree;
import org.example.hw_server.client.ui.View;
import org.example.hw_server.server.Server;
import org.example.hw_server.server.User;

public class Client {
    private View view;
    private Server server;

    private User user;

    //пройдена ли авторизация
    private boolean authorized;

    public Client(View view, Server server) {
        this.view = view;
        this.server = server;

        this.authorized = false;
    }

    private void connectToServer() {
        view.connectedToServer();
    }

    public void disconnectedFromServer() {
        view.disconnectedFromServer();
    }

    public void sendMessage(String message) {
        view.sendMessage(message);
    }

    public String getMessage() {
        return server.getMessage();
    }

    public void appendMessage(String message) {
        server.appendUserMessage(this.user,message);
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
        if (checkServerStartup()) {
            int verificationLog = server.checkVerification(ip, port, login, password);
            switch (verificationLog) {
                case 1 -> {
                    view.showNotification("Ip адрес не найден");
                }
                case 2 -> {
                    view.showNotification("Неверно указанный порт");
                }
                case 3 -> {
                    view.showNotification("Для авторизации введите логин и пароль");
                }
                case 4 -> {
                    view.showNotification("Не верный логин или пароль");
                }
                default -> {
                    this.user = server.findByLogin(login);
                    this.authorized = true;
                    server.addClient(this);
                    connectToServer();
                }
            }
        } else view.showNotification("Сервер не запущен");
    }

    public boolean checkServerStartup() {
        return server.isRun();
    }


    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public String getIp() {
        return server.getIpAddress();
    }


}
