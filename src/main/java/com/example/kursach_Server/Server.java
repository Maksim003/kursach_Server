package com.example.kursach_Server;

import com.example.kursach_Server.Model.DatabaseModel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static DatabaseModel databaseModel;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3345);
            System.out.println("Сервер запущен");
            System.out.println("Введите пароль от базы данных");
            String password = sc.nextLine();
            databaseModel = new DatabaseModel();
            databaseModel.connectToDb(password);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread thread = new ServerThread(clientSocket, databaseModel);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
