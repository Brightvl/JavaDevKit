package org.example.lesson_1.hw_1.client.widgets;


import org.example.lesson_1.hw_1.client.ClientGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ClientGUILoginPanel extends JPanel {

    private boolean isVisible;

    private String ip;
    private String port;

    private String login;
    private String password;

    private JTextField ipTextField;
    private JTextField portTextField;
    private JTextField loginTextField;
    private JTextField passwordTextField;

    // размеры окна
    private static final int WIDTH = 400;
    private static final int HEIGHT = 507;

    ClientGUI clientGUI;

    public ClientGUILoginPanel(ClientGUI clientGUI) {
        this.clientGUI = clientGUI;
        this.isVisible = true;


        //сетка фрейма
        createField();


    }

    /**
     * Создает поле для авторизации
     *
     * @return компонент панель
     */
    private Component createField() {
        JPanel serverPanel = new JPanel(new GridLayout(1, 2));
        this.ipTextField = (JTextField) createFieldIp();
        serverPanel.add(ipTextField);
        this.portTextField = (JTextField) createFieldPort();
        serverPanel.add(portTextField);

        JPanel authorizationPanel = new JPanel(new GridLayout(1, 2));
        this.loginTextField = (JTextField) createFieldLogin();
        authorizationPanel.add(loginTextField);
        this.passwordTextField = (JTextField) createFieldPassword();
        authorizationPanel.add(passwordTextField);

        setLayout(new BorderLayout());

        add(serverPanel, BorderLayout.NORTH);
        add(authorizationPanel, BorderLayout.CENTER);
        add(createAuthorizationButton(), BorderLayout.SOUTH);
        return this;
    }


    /**
     * Создает текстовое поле для ввода Ip
     *
     * @return Component
     */
    private Component createFieldIp() {
        JTextField ip = new JTextField();
        ip.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // При получении фокуса, если текст совпадает с placeholder, очищаем его
                if (ip.getText().equals("ip address")) {
                    ip.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // При потере фокуса, если текст пустой, устанавливаем placeholder
                if (ip.getText().isEmpty()) {
                    ip.setText("ip address");
                }
            }
        });
        return ip;
    }

    /**
     * Создает текстовое поле для ввода port
     *
     * @return Component
     */
    private Component createFieldPort() {
        JTextField port = new JTextField();
        port.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // При получении фокуса, если текст совпадает с placeholder, очищаем его
                if (port.getText().equals("port")) {
                    port.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // При потере фокуса, если текст пустой, устанавливаем placeholder
                if (port.getText().isEmpty()) {
                    port.setText("port");
                }
            }
        });
        return port;
    }

    /**
     * Создает текстовое поле для ввода логина
     *
     * @return Component
     */
    private Component createFieldLogin() {
        JTextField login = new JTextField();
        login.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // При получении фокуса, если текст совпадает с placeholder, очищаем его
                if (login.getText().equals("Login")) {
                    login.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // При потере фокуса, если текст пустой, устанавливаем placeholder
                if (login.getText().isEmpty()) {
                    login.setText("Login");
                }
            }
        });
        return login;
    }

    /**
     * Создает текстовое поле для ввода пароля
     *
     * @return Component
     */
    private Component createFieldPassword() {
        JTextField password = new JTextField();
        password.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // При получении фокуса, если текст совпадает с placeholder, очищаем его
                if (password.getText().equals("Password")) {
                    password.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // При потере фокуса, если текст пустой, устанавливаем placeholder
                if (password.getText().isEmpty()) {
                    password.setText("Password");
                }
            }
        });
        return password;
    }

    /**
     * Создает кнопку для подтверждения авторизации
     *
     * @return Component
     */
    private Component createAuthorizationButton() {
        JButton authorizationButton = new JButton("Авторизоваться");
        authorizationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Получаем значения из текстовых полей и сохраняем в переменные
                ip = ipTextField.getText();
                port = portTextField.getText();
                login = loginTextField.getText();
                password = passwordTextField.getText();
                clientGUI.checkVerification(ip, port, login, password);
            }
        });
        return authorizationButton;
    }


}


