package com.example.kursach_Server.Model;

public class Marks {

    private String id;
    private int coursework;
    private int economy;
    private int networks;
    private int programming;
    private int ergaticSystem;
    private int id_student;

    public Marks() {
    }

    public Marks(String id, int coursework, int economy, int networks, int programming, int ergaticSystem, int id_student) {
        this.id = id;
        this.coursework = coursework;
        this.economy = economy;
        this.networks = networks;
        this.programming = programming;
        this.ergaticSystem = ergaticSystem;
        this.id_student = id_student;
    }

    public Marks(int coursework, int economy, int networks, int programming, int ergaticSystem) {
        this.coursework = coursework;
        this.economy = economy;
        this.networks = networks;
        this.programming = programming;
        this.ergaticSystem = ergaticSystem;
    }

    public Marks(int coursework, int economy, int networks, int programming, int ergaticSystem, int id_student) {
        this.coursework = coursework;
        this.economy = economy;
        this.networks = networks;
        this.programming = programming;
        this.ergaticSystem = ergaticSystem;
        this.id_student = id_student;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCoursework() {
        return coursework;
    }

    public void setCoursework(int coursework) {
        this.coursework = coursework;
    }

    public int getEconomy() {
        return economy;
    }

    public void setEconomy(int economy) {
        this.economy = economy;
    }

    public int getNetworks() {
        return networks;
    }

    public void setNetworks(int networks) {
        this.networks = networks;
    }

    public int getProgramming() {
        return programming;
    }

    public void setProgramming(int programming) {
        this.programming = programming;
    }

    public int getErgaticSystem() {
        return ergaticSystem;
    }

    public void setErgaticSystems(int ergaticSystem) {
        this.ergaticSystem = ergaticSystem;
    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }
}
