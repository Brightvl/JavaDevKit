package org.example.hw_server.server.ui.widgets;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ServerLogPanel extends JPanel {

    // массив для логов
    private final List<String> logMessages;


    // виджеты
    private final JScrollPane scrollPane;
    private final JTextArea logTextArea;


    public ServerLogPanel() {
        this.logMessages = new ArrayList<>();

        setLayout(new BorderLayout(2, 1));

        // Создаем JTextArea для отображения логов, ставим запрет на редактирование
        logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        logTextArea.setLineWrap(true);  // включить автоматический перенос строки
        logTextArea.setWrapStyleWord(true);  // включить автоматический перенос слов

        // Добавляем текстовое поле в панель для скроллинга
        scrollPane = new JScrollPane(logTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //добавляем виджеты
        add((new JLabel("Server log: ", JLabel.CENTER)), BorderLayout.NORTH); // Название окна логов
        add(scrollPane, BorderLayout.CENTER);

        setVisible(false);
    }


    /**
     * Метод для добавления текста в log файл
     *
     * @param text текст логов
     */
    public void serverLogUpdate(String text) {
        this.logMessages.add(text);
        logTextArea.setText(text);

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setValue(verticalScrollBar.getMaximum());
        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());

        repaint();
    }
}
