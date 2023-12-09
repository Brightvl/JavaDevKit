package org.example.lesson_1.hw_1.client.widgets;

import javax.swing.*;
import java.awt.*;

/**
 * Панель отображения диалога
 */
public class MessageDisplayWindowPanel extends JPanel {

    private JScrollPane scrollPane;
    private JTextArea sendTextArea;
    private JTextArea receiveTextArea;

    public MessageDisplayWindowPanel() {

        setLayout(new BorderLayout(2, 1));

        // для отображения исходящих сообщений
        sendTextArea = new JTextArea();
        sendTextArea.setEditable(false); // запрет редактирования
        sendTextArea.setLineWrap(true);  // включить автоматический перенос строки
        sendTextArea.setWrapStyleWord(true);  // включить автоматический перенос слов

        // для отображения входящих сообщений
        receiveTextArea = new JTextArea();
        receiveTextArea.setEditable(false);
        receiveTextArea.setLineWrap(true);
        receiveTextArea.setWrapStyleWord(true);

        // разделяю поле для отправленных и принятых сообщений
        JPanel jPanel = new JPanel(new GridLayout(1, 2));
        jPanel.add(receiveTextArea);
        jPanel.add(sendTextArea);

        // Добавляем текстовое поле в панель для скроллинга
        scrollPane = new JScrollPane(jPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Добавляем виджеты

        add((new JLabel("User ", JLabel.CENTER)), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }



    // Метод для отображения сообщения
    public void appendSentMessage(String message) {
        String tempMessage = message.trim();
        if (!tempMessage.isEmpty()) {
            sendTextArea.append(message + "\n");
        }
    }


    // Метод для отображения сообщения
    public void appendReceiveMessage(String message) {
        String tempMessage = message.trim();
        if (!tempMessage.isEmpty()) {
            receiveTextArea.append(message + "\n");
        }
    }


}