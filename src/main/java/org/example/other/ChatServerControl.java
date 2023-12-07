package org.example.other;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChatServerControl extends JFrame {
    private JButton btnStart;
    private JButton btnStop;
    private JTextArea areaServerLog;
    private boolean isServerWorking = false;

    ChatServerControl() {
        btnStart = new JButton("Запустить сервер");
        btnStop = new JButton("Остановить сервер");
        areaServerLog = new JTextArea(30, 40);
        areaServerLog.setEditable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnStart);
        buttonPanel.add(btnStop);

        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(areaServerLog), BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 700);
        setTitle("ServerWindow Control");
        setVisible(true);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    areaServerLog.append("Сервер уже работает.\n");
                } else {
                    areaServerLog.append("Запуск сервера...\n");
                    isServerWorking = true;
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    areaServerLog.append("Остановка сервера...\n");
                    isServerWorking = false;
                } else {
                    areaServerLog.append("Сервер не запущен.\n");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChatServerControl());
    }
}


