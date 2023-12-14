package org.example.hw_server.server.ui.widgets;

import java.io.*;
import java.util.List;

public class LogManager {

    private File file;

    private List<String> listLogMessage;


    public LogManager(File file) {
        this.file = file;
    }


    /**
     * читает из файла
     * @throws IOException
     */
    public void readFileContents() throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    /**
     * Метод записывает в файл последовательность
     *
     * @throws IOException исключение ввода вывода
     */
    public void writeFileContents(String message) throws IOException {
        String tempMessage = message + '\n';
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true))) {
            bufferedWriter.write(tempMessage);
            System.out.println("Данные записаны в файл");
        }
    }


    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/org/example/lesson_1/hw_1/data/filename.txt");
        LogManager logManager = new LogManager(file);
        logManager.writeFileContents("Hello");
        logManager.readFileContents();
    }
}
