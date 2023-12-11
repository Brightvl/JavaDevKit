package org.example.lesson_1.hw_1.server.ui.widgets;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ServerLogPanel extends JPanel {

    // массив для логов
    private List<String> logMessages;


    // виджеты
    private JScrollPane scrollPane;
    private JTextArea logTextArea;


    public ServerLogPanel() {
        this.logMessages = new ArrayList<>();

        setLayout(new BorderLayout(2, 1));

        // Создаем JTextArea для отображения логов, ставим запрет на редактирование
        logTextArea = new JTextArea();
        logTextArea.setEditable(false);

        // Добавляем текстовое поле в панель для скроллинга
        scrollPane = new JScrollPane(logTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //добавляем виджеты
        add((new JLabel("Server log: ", JLabel.CENTER)), BorderLayout.NORTH); // Название окна логов
        add(scrollPane, BorderLayout.CENTER);
    }


    /**
     * Метод для добавления текста в log файл
     *
     * @param message сообщение для логирования
     */
    public void addLogMessage(String message) {
        this.logMessages.add(message);

        // Обновляем текст в JTextArea
        StringBuilder logText = new StringBuilder();
        for (int i = 0; i < logMessages.size(); i++) {
            logText.append(i + 1).append(". ").append(logMessages.get(i)).append("\n");
        }
        logTextArea.setText(logText.toString());

        // Прокручиваем до конца
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setValue(verticalScrollBar.getMaximum());

        repaint();
    }
}
