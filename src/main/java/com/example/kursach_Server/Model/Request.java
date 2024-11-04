package com.example.kursach_Server.Model;


public class Request {
    private String request;
    private Account account;
    private Students students;
    private Marks marks;
    private double basicS;

    public Request(String request, Account account, Students students, double basicS) {
        this.request = request;
        this.account = account;
        this.students = students;
        this.basicS = basicS;
    }

    public Request() {
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public double getBasicS() {
        return basicS;
    }

    public void setBasicS(double basicS) {
        this.basicS = basicS;
    }

    public Marks getMarks() {
        return marks;
    }

    public void setMarks(Marks marks) {
        this.marks = marks;
    }
}
