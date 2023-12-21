package org.example.hw_server;


import org.example.hw_server.client.ui.ClientGUI;
import org.example.hw_server.server.Server;

import java.awt.*;

public class GeneralChatApp {
    public static void main(String[] args) {
        //Создаем сервер + UI
        Server server = new Server();
        Point pointLocation = server.getServerGUI().getLocation();
        // Создаем UI клиента
        ClientGUI.createClientGUI(server, pointLocation, -server.getServerGUI().getWidth(), 0);
        ClientGUI.createClientGUI(server, pointLocation, server.getServerGUI().getWidth(), 0);


    }
}
