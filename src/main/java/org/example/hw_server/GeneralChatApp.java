package org.example.hw_server;


import org.example.hw_server.client.ui.ClientGUI;
import org.example.hw_server.server.Server;

/**
 * <p>
 *     1. Реализовать клиент-серверное приложение. Начало его можно увидеть в презентации к первому уроку, а можно
 * ориентироваться на скриншоты. Результат можно увидеть на скриншотах, которые также можно найти в материалах к уроку
 * <p>
 *     2. Клиентское приложение должно отправлять сообщения из текстового поля сообщения в серверное приложение по
 * нажатию
 * кнопки или по нажатию клавиши Enter на поле ввода сообщения;
 * <p>
 *     3. Продублировать импровизированный лог (историю) чата в файле;
 * <p>
 *     4. При запуске клиента чата заполнять поле истории из файла, если он существует. Обратите внимание, что чаще всего
 * история сообщений хранится на сервере и заполнение истории чата лучше делать при соединении с сервером, а не при открытии окна клиента.
 */
public class GeneralChatApp {
    public static void main(String[] args) {
        Server server = new Server();

        ClientGUI.createClient(server, server.getServerGUI().getLocation(), -server.getServerGUI().getWidth(), 0);
        ClientGUI.createClient(server, server.getServerGUI().getLocation(), server.getServerGUI().getWidth(), 0);

    }
}
