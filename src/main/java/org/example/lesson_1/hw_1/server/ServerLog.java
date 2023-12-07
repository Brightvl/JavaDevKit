package org.example.lesson_1.hw_1.server;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ServerLog extends JPanel {



    private List<String> logMessages;
    private static final int MESSAGE_MARGIN = 20; // Отступ между сообщениями

    private ServerWindow server;

    ServerLog(ServerWindow server) {
        this.logMessages = new ArrayList<>();
        this.server = server;
        add(new Label("Server log"));
    }


    public void addLogMessage(String message) {
        this.logMessages.add(message);

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.LIGHT_GRAY);

        // Отображаем сообщения в логе
        g.setColor(Color.BLACK);

        g.setFont(new Font("Arial", Font.BOLD, 12));
        int y = MESSAGE_MARGIN + 30; // начальный отступ
        for (int i = 0; i < logMessages.size(); i++) {
            g.drawString(i+1 + ". " + logMessages.get(i), MESSAGE_MARGIN, y);
            y +=  MESSAGE_MARGIN; // Увеличиваем y для следующего сообщения
        }

    }




}
