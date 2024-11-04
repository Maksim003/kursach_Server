package com.example.kursach_Server.Model;

import com.example.kursach_Server.Model.Account;
import com.example.kursach_Server.Model.Marks;
import com.example.kursach_Server.Model.Students;

import java.util.ArrayList;

public class Answer {
    private String answer;
    private ArrayList<Account> accounts;
    private ArrayList<Students> students;
    private Marks marks;
    private double data;
    private String salt;

    public Answer(String answer, ArrayList<Account> accounts, ArrayList<Students> students, Marks marks, double data, String salt) {
        this.answer = answer;
        this.accounts = accounts;
        this.students = students;
        this.marks = marks;
        this.data = data;
        this.salt = salt;
    }


    public Answer() {
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public ArrayList<Students> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Students> students) {
        this.students = students;
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Marks getMarks() {
        return marks;
    }

    public void setMarks(Marks marks) {
        this.marks = marks;
    }
}
