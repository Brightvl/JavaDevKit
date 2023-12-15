package org.example.hw_server.server;

public interface ViewServer {
    void showLog(String message);
    void runServer();
    void stopServer();
    void showNotification(String message);
}
