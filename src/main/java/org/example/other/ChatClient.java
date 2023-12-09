package org.example.other;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class ChatClient extends JFrame {
    private JTextField txtFieldLogin;
    private JTextField txtFieldPassword;
    private JTextField txtFieldServerIP;
    private JTextField txtFieldServerPort;
    private JTextArea areaMessage;
    private JTextArea areaChatHistory;
    private JList<String> userList;
    private JButton btnConnect;
    private JButton btnSend;
    private String chatHistoryFile = "./chat_history.txt";

    ChatClient() {
        txtFieldLogin = new JTextField();
        txtFieldPassword = new JTextField();
        txtFieldServerIP = new JTextField();
        txtFieldServerPort = new JTextField();
        areaMessage = new JTextArea(10, 40);
        areaChatHistory = new JTextArea(20, 40);
        userList = new JList<>(new String[]{"User1", "User2", "User3"});
        btnConnect = new JButton("Подключиться");
        btnSend = new JButton("Отправить");

        JPanel loginPanel = new JPanel(new GridLayout(2, 2));
        loginPanel.add(new JLabel("   Логин:"));
        loginPanel.add(txtFieldLogin);
        loginPanel.add(new JLabel("   Пароль:"));
        loginPanel.add(txtFieldPassword);

        JPanel serverPanel = new JPanel(new GridLayout(2, 2));
        serverPanel.add(new JLabel("   IP сервера:"));
        serverPanel.add(txtFieldServerIP);
        serverPanel.add(new JLabel("   Порт сервера:"));
        serverPanel.add(txtFieldServerPort);

        JPanel connectPanel = new JPanel();
        connectPanel.add(btnConnect);

        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.add(new JScrollPane(areaMessage), BorderLayout.CENTER);
        messagePanel.add(btnSend, BorderLayout.EAST);

        JPanel chatPanel = new JPanel(new BorderLayout());
        chatPanel.add(new JScrollPane(areaChatHistory), BorderLayout.CENTER);
        chatPanel.add(userList, BorderLayout.EAST);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(loginPanel);
        mainPanel.add(serverPanel);
        mainPanel.add(connectPanel);
        mainPanel.add(messagePanel);
        mainPanel.add(chatPanel);

        add(mainPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 700);
        setTitle("Chat User");
        setVisible(true);

        loadChatHistory();

        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = txtFieldLogin.getText();
                String password = txtFieldPassword.getText();
                String serverIP = txtFieldServerIP.getText();
                String serverPort = txtFieldServerPort.getText();

                // Connect to the server
                areaChatHistory.append("Подключение к серверу...\n");
                // Code for connecting to the server

                areaChatHistory.append("Успешное подключение!\n");
            }
        });

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        areaMessage.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void sendMessage() {
        String message = areaMessage.getText();
        if (!message.isEmpty()) {
            areaChatHistory.append("Вы: " + message + "\n");
            appendChatHistory("Вы: " + message);
            areaMessage.setText("");
        }
    }

    private void loadChatHistory() {
        File file = new File(chatHistoryFile);
        if (file.exists()) {
            try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = fileReader.readLine()) != null) {
                    areaChatHistory.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void appendChatHistory(String message) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(chatHistoryFile, true))) {
            fileWriter.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChatClient());
    }
}
