package org.example.lesson_1.hw_1.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame {

    private boolean isServerWorking;

    // размеры окна
    private static final int WIDTH = 400;
    private static final int HEIGHT = 507;

    private JButton serverRun;
    private JButton serverStop;

    ServerLog serverLog; // окно логирования

    public ServerWindow() {
        //region параметры окна
        setTitle("Server ver. 0.00000000000001"); // название окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // по центру экрана
        setResizable(false); // запрет на растягивание окна
        //endregion

        this.serverLog = new ServerLog(this);

        //region расположение виджетов
        JPanel mainPanel = new JPanel(new GridLayout(0, 2));
        // создание кнопок
        mainPanel.add(createServerRunButton());
        mainPanel.add(createServerStopButton());
        // расстановка
        add(serverLog);
        add(mainPanel, BorderLayout.SOUTH);
        //endregion

    }

    /**
     * Для включения окна сервера
     */
    public void startServer() {
        super.setVisible(true);
    }

    /**
     * Создает кнопку запуска сервера
     *
     * @return компонент кнопка
     */
    private Component createServerRunButton() {
        this.serverRun = new JButton("Run server");
        serverRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isServerWorking) {
                    serverLog.addLogMessage("Server launched");
                } else {
                    serverLog.addLogMessage("Server already run");
                }
                isServerWorking = true;


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
        this.serverStop = new JButton("Stop server");
        // слушатель кнопки выход (анонимный класс)
        serverStop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                serverLog.addLogMessage("Server stopped");
                isServerWorking = false;
                System.exit(0);

            }
        });
        return serverStop;
    }

}
