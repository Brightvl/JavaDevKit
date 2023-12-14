package org.example.hw_server.client.widgets;

import org.example.hw_server.client.ClientGUI;

import javax.swing.*;
import java.awt.*;

/**
 * Панель отображения сообщения чата
 */
public class MessageDisplayWindowPanel extends JPanel {

    // виджеты
    private JScrollPane scrollPane;
    private JTextArea sendTextArea;
    private JTextArea receiveTextArea;
    private ClientGUI clientGUI;

    /**
     * Конструктор
     */
    public MessageDisplayWindowPanel(ClientGUI clientGUI) {
        this.clientGUI = clientGUI;
        // компоновщик
        setLayout(new BorderLayout(2, 1));

        // для отображения исходящих сообщений
        sendTextArea = new JTextArea("\n\n");
        sendTextArea.setEditable(false); // запрет редактирования
        sendTextArea.setLineWrap(true);  // включить автоматический перенос строки
        sendTextArea.setWrapStyleWord(true);  // включить автоматический перенос слов

        // для отображения входящих сообщений
        receiveTextArea = new JTextArea("Общий чат\n\n");
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
        add((new JLabel("General chat", JLabel.CENTER)), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }


    /**
     * Метод для вывода сообщения в поле отправленные сообщения
     * @param message отправленное сообщение
     */
    public void appendSentMessage(String message) {
        String tempMessage = message.trim();
        if (!tempMessage.isEmpty()) {
            sendTextArea.append(message + "\n");

        }
    }

    /**
     * Метод для отображения принятого сообщения в общем чате
     * @param message принятое сообщение
     */
    public void appendReceiveMessage(String message) {
        String tempMessage = message.trim();
        if (!tempMessage.isEmpty()) {
            receiveTextArea.append(message + "\n");
        }

    }

}
