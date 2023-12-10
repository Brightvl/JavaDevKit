package org.example.lesson_1.hw_1.client.widgets;


import org.example.lesson_1.hw_1.client.ClientGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class VerificationPanel extends JPanel {

    private boolean isVisible;

    // введенные данные сервера
    private String ipAddress;
    private String port;

    // введенные данные пользователя
    private String login;
    private String password;

    // поля с данными
    private JTextField ipAddressTextField;
    private JTextField portTextField;
    private JTextField loginTextField;
    private JTextField passwordTextField;

    // размеры окна
    private static final int WIDTH = 400;
    private static final int HEIGHT = 507;

    // для взаимодействия с main основным клиентом
    private ClientGUI clientGUI;

    public VerificationPanel(ClientGUI clientGUI) {
        this.clientGUI = clientGUI;
        this.isVisible = true;
        //создаем поля ввода параметров пользователя
        createField();
    }

    /**
     * Создает поле для авторизации
     *
     * @return поле ввода данных
     */
    private Component createField() {
        // панель параметров сервера
        JPanel serverPanel = new JPanel(new GridLayout(1, 2));
        this.ipAddressTextField = (JTextField) createFieldIp();
        serverPanel.add(ipAddressTextField);
        this.portTextField = (JTextField) createFieldPort();
        serverPanel.add(portTextField);

        // панель параметров пользователя
        JPanel authorizationPanel = new JPanel(new GridLayout(1, 2));
        this.loginTextField = (JTextField) createFieldLogin();
        authorizationPanel.add(loginTextField);
        this.passwordTextField = (JTextField) createFieldPassword();
        authorizationPanel.add(passwordTextField);

        // компоновщик
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
        JTextField ipAddress = new JTextField();
        ipAddress.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // При получении фокуса, если текст совпадает с placeholder, очищаем его
                if (ipAddress.getText().equals("Ip address")) {
                    ipAddress.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // При потере фокуса, если текст пустой, устанавливаем placeholder
                if (ipAddress.getText().isEmpty()) {
                    ipAddress.setText("Ip address");
                }
            }
        });
        return ipAddress;
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
                if (port.getText().equals("Port")) {
                    port.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // При потере фокуса, если текст пустой, устанавливаем placeholder
                if (port.getText().isEmpty()) {
                    port.setText("Port");
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
                ipAddress = ipAddressTextField.getText();
                port = portTextField.getText();
                login = loginTextField.getText();
                password = passwordTextField.getText();
                clientGUI.checkVerification(ipAddress, port, login, password);
            }
        });
        return authorizationButton;
    }
}


