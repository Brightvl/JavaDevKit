package org.example.lesson_1.hw_1.client.widgets;

import org.example.lesson_1.hw_1.client.ClientGUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Панель для ввода и отправки сообщений
 */
public class SendMessagePanel extends JPanel {



    private JTextField textInputPanel; // для однострочного ввода сообщения(чтобы не было бага с размером окна)

    //mainFrame
    ClientGUI clientGUI;

    //Widgets
    private JButton sendMessageButton;
    private String sendMessage;


    /**
     * Конструктор
     * @param clientGUI главное окно клиента
     */
    public SendMessagePanel(ClientGUI clientGUI) {
        this.clientGUI = clientGUI;

        setLayout(new BorderLayout(1, 2));
        add(createTextInputArea());
        add(createSendMessageButton(), BorderLayout.EAST);
    }

    /**
     * Создает панель ввода данных, обрабатывая нажатия клавиатуры
     * @return компонент текстовое поле
     */
    private Component createTextInputArea() {
        textInputPanel = new JTextField();
        textInputPanel.setEditable(true);

        // Добавляем KeyListener к textInputPanel для обработки нажатия клавиши Enter
        textInputPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });
        return textInputPanel;
    }

    /**
     * Создание кнопки отправки сообщения обрабатывая нажатия мыши
     * @return
     */
    private Component createSendMessageButton() {
        sendMessageButton = new JButton("Send");

        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        return sendMessageButton;
    }



    /**
     * Метод для отправки сообщения
     */
    private void sendMessage() {
        sendMessage = textInputPanel.getText();

        // Очищаем текстовое поле
        textInputPanel.setText("");

        clientGUI.appendSentMessage(sendMessage.trim());


    }


}
