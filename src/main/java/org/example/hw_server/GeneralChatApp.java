package org.example.hw_server;


import org.example.hw_server.client.ui.ClientGUI;
import org.example.hw_server.server.Server;

public class GeneralChatApp {
    public static void main(String[] args) {
        Server server = new Server();

        ClientGUI.createClient(server, server.getServerGUI().getLocation(), -server.getServerGUI().getWidth(), 0);
        ClientGUI.createClient(server, server.getServerGUI().getLocation(), server.getServerGUI().getWidth(), 0);

    }
}
