package org.example.hw_server.client.ui;

public interface View {
    void sendMessage(String message) ;
    void connectedToServer();
    void disconnectedFromServer() ;

    void showNotification(String message);
}
