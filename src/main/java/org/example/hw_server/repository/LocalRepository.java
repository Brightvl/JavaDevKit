package org.example.hw_server.repository;

import java.util.ArrayList;
import java.util.List;

public class LocalRepository implements Repository{
    private final List<User> userList;

    public LocalRepository() {
        this.userList = new ArrayList<>();
        userList.add(new User("1","1"));
        userList.add(new User("Nikolai","4321"));
    }

    public List<User> getUser() {
        return userList;
    }
}
