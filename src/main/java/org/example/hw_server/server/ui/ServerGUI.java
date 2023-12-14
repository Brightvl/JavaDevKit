package org.example.hw_server.server.ui;

import org.example.hw_server.server.Server;
import org.example.hw_server.server.User;
import org.example.hw_server.server.ui.widgets.ServerLogPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame {

    private Server server;

    // размеры окна
    private static final int WIDTH = 400;
    private static final int HEIGHT = 507;

    // виджеты
    private JButton serverRun;
    private JButton serverStop;
    private ServerLogPanel serverLog; // окно логирования


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

    }
    //endregion


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
                if (!server.isServerWorking()) {
                    server.setServerWorking(true);
                    serverLog.addLogMessage("Server launched");
                } else {
                    serverLog.addLogMessage("Server already run");
                }
                server.setServerWorking(true);

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
                exitProgram();
            }
        });
        return serverStop;
    }


    /**
     * Запускает GUi
     */
    public void runProgram() {
        super.setVisible(true);
    }
    /**
     * Производит действия для закрытия программы
     */
    private void exitProgram() {
        serverLog.addLogMessage("Server stopped");
        server.stopClientGUI();
    }

    /**
     * Получает сообщения от из сервера
     * @param message полученное сообщение
     */
    public void getServerMessage(String message) {
        serverLog.addLogMessage(message);

    }


    /**
     * Добавляет сообщение отправленное пользователем в логи сервера
     * @param user пользователь
     * @param message сообщение
     */
    public void appendUserMessageToServerLog(User user, String message) {
        getServerMessage("User " + user.getLogin() + " wrote " + message + " in general chat");
    }
}
