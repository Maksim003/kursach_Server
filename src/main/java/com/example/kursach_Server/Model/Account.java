package com.example.kursach_Server.Model;

public class Account {
    private int id;
    private String login;
    private String password;
    private int role;
    private int access;
    private String salt;

    public Account(String login, String password, int role, int access, String salt) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.access = access;
        this.salt = salt;
    }

    public Account(int id, String login, int role) {
        this.id = id;
        this.login = login;
        this.role = role;
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
