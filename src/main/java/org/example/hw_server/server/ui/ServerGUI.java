package org.example.hw_server.server.ui;

import org.example.hw_server.server.Server;
import org.example.hw_server.server.ViewServer;
import org.example.hw_server.server.ui.widgets.ServerLogPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class ServerGUI extends JFrame implements ViewServer {

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


        settingWindow();


        JPanel mainPanel = new JPanel(new GridLayout(0, 2));
        // создание кнопок
        mainPanel.add(createServerRunButton());
        mainPanel.add(createServerStopButton());
        // расстановка
        add(serverLog);
        add(mainPanel, BorderLayout.SOUTH);



        serverLog.serverLogUpdate(getLogMessage());
        super.setVisible(true);
    }

    private void settingWindow() {
        setTitle("Server ver. 0.00000000000002"); // название окна

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // по центру экрана
        setResizable(false); // запрет на растягивание окна
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
                runServer();
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
                stopServer();

            }
        });
        return serverStop;
    }
    //endregion

    /**
     * Сохраняет сообщения в лог файле и обновляет их на экране
     * @param text текст из фала логов
     */
    public void serverLogUpdate(String text) {
        saveInLog(text);
        showLog(getLogMessage());
    }

    public void saveInLog(String message) {
        server.getLogger().save(message);
    }

    public String getLogMessage() {
        return server.getLogger().load();
    }

    @Override
    public void showLog(String message) {
        serverLog.serverLogUpdate(message);
    }

    @Override
    public void runServer() {
        if (!server.isRun()) {
            server.setRun(true);
            serverLogUpdate("[Server] launched");
        } else {
            serverLogUpdate("[Server] already run");
        }
        server.setRun(true);

        serverLog.setVisible(true);
    }

    @Override
    public void stopServer() {
        serverLogUpdate("[Server] stopped");
        if (server.isRun()) {
            showNotification("Сервер остановлен");
        }
        server.stopClientGUI();
        serverLog.setVisible(false);

    }

    @Override
    public void showNotification(String message) {
        JOptionPane.showMessageDialog(this,message);
    }


}
