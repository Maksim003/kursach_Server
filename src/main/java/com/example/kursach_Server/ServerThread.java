package com.example.kursach_Server;

import com.example.kursach_Server.Model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServerThread extends Thread {
    Socket client;
    DatabaseModel databaseModel;

    public ServerThread(Socket client, DatabaseModel databaseModel) {
        this.client = client;
        this.databaseModel = databaseModel;
    }

    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            while (true) {
                String inJson = "", outJson = "";
                try {
                    inJson = in.readUTF();
                } catch (IOException e) {
                }
                System.out.println("Клиент прислал: " + inJson);
                Request request = objectMapper.readValue(inJson, Request.class);
                Answer answer = sendData(request);
                outJson = objectMapper.writeValueAsString(answer);
                System.out.println(outJson);
                out.writeUTF(outJson);
                out.flush();
                break;
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Answer sendData(Request request) throws SQLException {
        ArrayList<Account> accounts = new ArrayList<>();
        ArrayList<Students> students = new ArrayList<>();
        Answer answer = new Answer();
        answer.setAnswer(request.getRequest());
        switch (request.getRequest()) {
            case "getAccountsWithAccess":
                accounts = databaseModel.getTableViewAccounts(1);
                answer.setAccounts(accounts);
                break;
            case "getStudents":
                students = databaseModel.getTableViewStudents();
                answer.setStudents(students);
                break;
            case "addAcc":
                databaseModel.signUp(request.getAccount());
                break;
            case "getSalt":
                answer.setSalt(databaseModel.getUserSalt(request.getAccount().getLogin()));
                break;
            case "getAcc":
                accounts.add(databaseModel.getUser(request.getAccount()));
                answer.setAccounts(accounts);
                break;
            case "changePass":
                databaseModel.changePass(request.getAccount().getLogin(), request.getAccount().getPassword());
                break;
            case "getAccountsWithoutAccess":
                accounts = databaseModel.getTableViewAccounts(0);
                answer.setAccounts(accounts);
                break;
            case "changeRole":
                if (request.getAccount().getRole() == 0) databaseModel.changeRole(request.getAccount().getLogin(), 1);
                else databaseModel.changeRole(request.getAccount().getLogin(), 0);
                break;
            case "deleteAcc":
                databaseModel.deleteAccount(request.getAccount().getLogin());
                break;
            case "changeAccess":
                databaseModel.changeAccess(request.getAccount().getLogin());
                break;
            case "addStudent":
                databaseModel.addStudents(request.getStudents());
                break;
            case "addMarks":
                students = databaseModel.getTableViewStudents();
                databaseModel.addMarks(request.getMarks(), students.get(students.size() - 1).getId() );
                databaseModel.addScholarship(students.get(students.size() - 1).getId() );
                break;
            case "delStudent":
                databaseModel.deleteMarks(request.getStudents().getId());
                databaseModel.deleteScholarship(request.getStudents().getId());
                databaseModel.deleteStudent(request.getStudents().getId());
                break;
            case "getMarks":
                Marks marks = databaseModel.getMarks(request.getStudents().getId());
                answer.setMarks(marks);
                break;
            case "changeStudent":
                databaseModel.changeDataMarks(request.getMarks());
                databaseModel.changeDataStudent(request.getStudents());
                break;
            case "getBasicS":
                answer.setData(databaseModel.getBasicScholarship());
                break;
            case "changeBasicS":
                databaseModel.changeBasicScholarship(request.getBasicS());
                break;
            case "calculate":
                if (request.getMarks().getId_student() == 0) {
                    students = databaseModel.getTableViewStudents();
                    request.getMarks().setId_student(students.get(students.size() - 1).getId());
                }
                databaseModel.calculateStudent(request.getMarks());
                break;
            case "updateScholarship":
                databaseModel.updateScholarship();
                break;
            case "getScholarship" :
                answer.setData(databaseModel.getScholarship(request.getStudents().getId()));
                break;
        }
        return answer;
    }
}
