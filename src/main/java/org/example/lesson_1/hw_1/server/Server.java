package org.example.lesson_1.hw_1.server;

import java.util.ArrayList;
import java.util.List;

public class Server {

    private static String ip = "1111";
    private static String port = "8888";


    private boolean isServerWorking;
    private List<User> userList;

    public Server() {
        this.userList = new ArrayList<>();
    }

    public String allMessage;


    public void addUser(User user) {
        userList.add(user);

    }

    //region геттеры сеттеры
    public boolean isServerWorking() {
        return isServerWorking;
    }

    public void setServerWorking(boolean serverWorking) {
        isServerWorking = serverWorking;
    }

    public boolean checkVerification(String ip, String port, String login, String password) {
        if (!ip.equals(Server.ip)) {
            System.out.println("ip не найден");
            return false;
        }
        if (!port.equals(Server.port)) {
            System.out.println("порт не найден");
            return false;
        }

        User user = new User("andrei", "1234");

        if (user.getLogin().equalsIgnoreCase(login) && user.getPassword().equalsIgnoreCase(password)) {
            System.out.println("все ок");
            return true;
        } else {
            System.out.println("логин и пароль не верен");
            return false;
        }
    }

    private User findByLogin(String login) {
        for (User user : userList
        ) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    public void appendToAllMessage(String message) {
//        StringBuilder stringBuilder = new StringBuilder(allMessage);
//        stringBuilder.append("\n" + message);
//        System.out.println(stringBuilder);
//        this.allMessage = stringBuilder.toString();
        allMessage = message;
    }

    //endregion



}
