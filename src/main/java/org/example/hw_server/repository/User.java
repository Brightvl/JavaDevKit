package org.example.hw_server.repository;

public class User {

    static int clientId = 0;
    private final String login;
    private final String password;


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



