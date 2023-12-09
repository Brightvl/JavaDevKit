package org.example.lesson_1.hw_1;


import org.example.lesson_1.hw_1.client.ClientGUI;
import org.example.lesson_1.hw_1.server.Server;
import org.example.lesson_1.hw_1.server.User;

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
        server.addUser(new User("Andrei,","1234"));
        server.addUser(new User("Nikolai,","4321"));


        ClientGUI.createClient(server,"1", server.getServerGUI().getLocation(), -server.getServerGUI().getWidth(), 0);
        ClientGUI.createClient(server, "2", server.getServerGUI().getLocation(), server.getServerGUI().getWidth(), 0);

    }


}
