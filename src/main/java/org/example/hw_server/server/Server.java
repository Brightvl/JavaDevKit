package org.example.hw_server.server;

import org.example.hw_server.client.Client;
import org.example.hw_server.repository.LocalRepository;
import org.example.hw_server.repository.Repository;
import org.example.hw_server.server.ui.ServerGUI;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Server {


    // адрес сервера
    private final String ipAddress = "192.168.168.255";
    private static final String port = "8.8.8.8";

    // состояние
    private boolean run;

    // хранилища
    private final List<Client> clients;
    private final Repository repository;


    private final File LOG_FILE_PATH = new File("src/main/java/org/example/hw_server/data/log.txt");
    private final File ALL_MESSAGE_FILE_PATH = new File("src/main/java/org/example/hw_server/data/all_message.txt");

    //окно клиента
    private final ServerGUI serverGUI;


    /**
     * Конструктор
     */
    public Server() {
        this.run = false;
        this.clients = new ArrayList<>();
        this.repository = new LocalRepository();

        serverGUI = new ServerGUI(this);
    }


    /**
     * Проерка верификации пользователя
     *
     * @param ipAddress ip адрес
     * @param port      порт
     * @param login     логин
     * @param password  пароль
     * @return значение ошибки
     */
    public int checkVerification(String ipAddress, String port, String login, String password) {
        if (!ipAddress.equals(ipAddress)) return 1;
        if (!port.equals(Server.port)) return 2;
        User user = findByLogin(login);

        if (user == null) return 3;
        if (!(user.getLogin().equalsIgnoreCase(login) &&
                user.getPassword().equalsIgnoreCase(password))) return 4;
        appendMessageToServerLog("User verification " + login + " was successful");
        return 0;
    }

    /**
     * Поиск по логину в архиве пользователей сервера
     * @param login логин
     * @return
     */
    public User findByLogin(String login) {
        for (User user : repository.getUser()
        ) {
            if (user.getLogin().equalsIgnoreCase(login)) {
                return user;
            }
        }
        return null;
    }


    /**
     * Получает сообщение отправленное клиентом и добавляет его в общий чат
     */
    public String getMessage() {
        return readMessage();
    }

    /**
     * Получает сообщение отправленное клиентом и добавляет его в общий чат
     */
    public void sendMessageToGeneralChat() {
        for (Client client : clients
        ) {
            client.sendMessage(readMessage());
        }
    }



    /**
     * Добавляет отправленное сообщение в логи сервера
     * @param message сообщение
     */
    private void appendMessageToServerLog(String message) {
        saveInLog(message);
        serverGUI.appendMessageToServerLog(readLog());
    }

    /**
     * Добавляет пользовательское сообщение в логи сервера
     * @param message сообщение
     */
    public void appendUserMessage(User user, String message) {
        appendMessageToServerLog("User [" + user.getLogin() + "] wrote {" + message + "} in general chat");

        saveInMessage(user.getLogin() + ": " + message);
        sendMessageToGeneralChat();
    }


    public void stopClientGUI() {
        this.run = false;
        for (Client client : clients
        ) {
            client.disconnectedFromServer();
        }

    }


    public void addClient(Client client) {
        clients.add(client);
    }

    public ServerGUI getServerGUI() {
        return serverGUI;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * @return Возвращает bool значение состояния сервера
     */
    public boolean isRun() {
        return run;
    }

    /**
     * Меняет значение состояния сервера
     *
     * @param run boolean
     */
    public void setRun(boolean run) {
        this.run = run;
    }

    /**
     * Создает форматированное время
     *
     * @return время в формате dd/MM/yy HH:mm
     */
    private String getDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Задаем формат даты и времени
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        // Форматируем текущую дату и время
        return '['+currentDateTime.format(formatter)+']';
    }

    /**
     * Запись содержимого в файл
     *
     * @param message сообщение
     */
    public void saveInLog(String message) {

        try (FileWriter writer = new FileWriter(LOG_FILE_PATH, true)) {
            writer.write(getDateTime() + " " + message);
            writer.write("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Считывание содержимого файла
     *
     * @return
     */
    public String readLog() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_FILE_PATH);) {
            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e) {
            saveInLog("Run server GUI"); // рекурсивно создаю файл
            return readLog();
        }

    }

    public void saveInMessage(String message) {
        try (FileWriter writer = new FileWriter(ALL_MESSAGE_FILE_PATH, true)) {
            writer.write(getDateTime() + " " + message);
            writer.write("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Считывание содержимого файла сообщений
     *
     * @return
     */
    public String readMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(ALL_MESSAGE_FILE_PATH);) {
            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e) {
            saveInMessage(""); // рекурсивно создаю файл
            return readLog();
        }

    }

}
