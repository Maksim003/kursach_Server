package com.example.kursach_Server.Model;

public class Students {
    private Integer id;
    private String surname;
    private String name;
    private String patronymic;
    private String date;

    public Students(String surname, String name, String patronymic, String date) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.date = date;
    }

    public Students(Integer id, String surname, String name, String patronymic, String date) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.date = date;
    }

    public Students() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}