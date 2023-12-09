package org.example.lesson_1.hw_1.server;

public class User {

    static int clientId = 0;
    private String login;
    private String password;


    public User(String name, String password) {
        this.login = name;
        this.password = password;
        clientId++;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}



