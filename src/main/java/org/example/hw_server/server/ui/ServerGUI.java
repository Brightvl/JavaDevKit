package org.example.hw_server.server.ui;

import org.example.hw_server.server.Server;
import org.example.hw_server.server.ui.widgets.ServerLogPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class ServerGUI extends JFrame {

    private final Server server;

    Date date = new Date();

    // размеры окна
    private static final int WIDTH = 400;
    private static final int HEIGHT = 507;

    private final ServerLogPanel serverLog; // окно логирования


    //region Конструктор
    public ServerGUI(Server server) {
        this.server = server;
        this.serverLog = new ServerLogPanel();


        //region параметры окна
        setTitle("Server ver. 0.00000000000001"); // название окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // по центру экрана
        setResizable(false); // запрет на растягивание окна
        //endregion


        //region расположение виджетов

        JPanel mainPanel = new JPanel(new GridLayout(0, 2));
        // создание кнопок
        mainPanel.add(createServerRunButton());
        mainPanel.add(createServerStopButton());
        // расстановка
        add(serverLog);
        add(mainPanel, BorderLayout.SOUTH);

        //endregion

        serverLog.serverLogUpdate(getLog());
        super.setVisible(true);
    }
    //endregion

    //region Виджеты

    /**
     * Создает кнопку запуска сервера
     * @return компонент кнопка
     */
    private Component createServerRunButton() {
        // виджеты
        JButton serverRun = new JButton("Run server");
        serverRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!server.isRun()) {
                    server.setRun(true);
                    serverLogUpdate("Server launched");
                } else {
                    serverLogUpdate("Server already run");
                }
                server.setRun(true);

            }
        });
        return serverRun;
    }

    /**
     * Создает кнопку остановки сервера
     *
     * @return компонент кнопка
     */
    private Component createServerStopButton() {
        JButton serverStop = new JButton("Stop server");
        // слушатель кнопки выход (анонимный класс)
        serverStop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                serverLogUpdate("Server stopped");
                server.stopClientGUI();
            }
        });
        return serverStop;
    }
    //endregion


    //region Логика сообщений
    /**
     * Сохраняет сообщения в лог файле и обновляет их на экране
     * @param text текст из фала логов
     */
    public void serverLogUpdate(String text) {
        saveInLog(text);
        serverLog.serverLogUpdate(getLog());
    }

    public void saveInLog(String message) {
        server.saveInLog(message);
    }

    public String getLog() {
        return server.readLog();
    }

    public void appendMessageToServerLog(String message) {
        serverLog.serverLogUpdate(message);
    }

    //endregion
}
