package com.example.kursach_Server.Model;

import com.example.kursach_Server.Database.Database;
import com.example.kursach_Server.Model.Account;
import com.example.kursach_Server.Model.Marks;
import com.example.kursach_Server.Model.Students;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DatabaseModel {
    Database db = new Database();

    public void connectToDb(String password) {
        db.createDbConnection(password);
    }

    public String getUserSalt(String log) {
        String select = "SELECT * FROM users WHERE login = " + "\"" + log + "\"";
        String salt = "";
        ResultSet resultSet = db.getData(select);
        try {
            if (resultSet.next()) {
                salt = resultSet.getString("salt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salt;
    }

    public void signUp(Account accounts) {
        String insertFields = "INSERT INTO users(login, password, role, access, salt) VALUES ('";
        String insertValues = accounts.getLogin() + "','" + accounts.getPassword() + "','" + accounts.getRole() + "','" + accounts.getAccess() + "','" + accounts.getSalt() + "')";
        String insert = insertFields + insertValues;
        db.updateData(insert);
    }


    public void addStudents(Students students) {
        String insertFields = "INSERT INTO students(surname, name, patronymic, date) VALUES ('";
        String insertValues = students.getSurname() + "','" + students.getName() + "','" + students.getPatronymic() + "','" + students.getDate() + "')";
        String insert = insertFields + insertValues;
        db.updateData(insert);
    }

    public void addMarks(Marks marks, int id) {
        String insertFields = "INSERT INTO marks(coursework, economy, networks, programming, ergaticSystem, id_students) VALUES ('";
        String insertValues = marks.getCoursework() + "','" + marks.getEconomy() + "','" + marks.getNetworks() + "','" + marks.getProgramming()+
        "','" + marks.getErgaticSystem() + "','" + id + "')";
        String insert = insertFields + insertValues;
        db.updateData(insert);
    }

    public Account getUser(Account account) {
        String select = "SELECT * FROM users WHERE login = " + "\"" + account.getLogin() + "\"" + " AND password = " + "\"" + account.getPassword() + "\"";
        ResultSet resultSet = db.getData(select);
        Account account1 = new Account();
        try {
            if (resultSet.next()) {
                account1.setLogin(account.getLogin());
                account1.setPassword(account.getPassword());
                account1.setRole(resultSet.getInt("role"));
                account1.setAccess(resultSet.getInt("access"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account1;
    }

    public void changePass(String log, String pass) {
        String select = "UPDATE users SET password = " + "\"" + pass + "\"" + " WHERE login = " + "\"" + log + "\"";
        db.updateData(select);
    }

    public ArrayList<Account> getTableViewAccounts(int access) throws SQLException {
        ArrayList<Account> accounts = new ArrayList<>();
        String select = "SELECT * FROM users WHERE access = " + access;
        ResultSet resultSet = db.getData(select);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String login = resultSet.getString("login");
            int role = resultSet.getInt("role");
            accounts.add(new Account(id, login, role));

        }
        return accounts;
    }

    /*public ArrayList<com.example.kursach_Server.Model.Students> getTableViewStudents() throws SQLException {
        ArrayList<com.example.kursach_Server.Model.Students> students = new ArrayList<>();
        String select = "SELECT * FROM students";
        ResultSet resultSet = db.getData(select);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String fio = resultSet.getString("fio");
            String date = resultSet.getDate("date").toString();
            int math = resultSet.getInt("mathAnalysis");
            int physics = resultSet.getInt("programming");
            int english = resultSet.getInt("english");
            int informatics = resultSet.getInt("physics");
            double basicScholarship = resultSet.getDouble("basicScholarship");
            double scholarship = resultSet.getDouble("scholarship");
            scholarship = Math.round(scholarship * 100.0) / 100.0;
            students.add(new com.example.kursach_Server.Model.Students(id, fio, date, math, physics, english, informatics, basicScholarship, scholarship));
        }
        return students;
    }*/

    public ArrayList<Students> getTableViewStudents() throws SQLException {
        ArrayList<Students> students = new ArrayList<>();
        String select = "SELECT * FROM students";
        ResultSet resultSet = db.getData(select);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String patronymic = resultSet.getString("patronymic");
            String date = resultSet.getDate("date").toString();
            //double basicScholarship = resultSet.getDouble("basicScholarship");
            //double scholarship = resultSet.getDouble("scholarship");
            //scholarship = Math.round(scholarship * 100.0) / 100.0;
            students.add(new Students(id, surname, name, patronymic, date));
        }
        return students;
    }

    public Marks getMarks(int id) throws SQLException {
        Marks marks = new Marks();
        String select = "SELECT * FROM marks WHERE id_students = " + id;
        ResultSet resultSet = db.getData(select);
        if (resultSet.next()) {
            marks.setCoursework(resultSet.getInt("coursework"));
            marks.setEconomy(resultSet.getInt("economy"));
            marks.setErgaticSystems(resultSet.getInt("ergaticSystem"));
            marks.setNetworks(resultSet.getInt("networks"));
            marks.setProgramming(resultSet.getInt("programming"));
        }
        return marks;
    }

    public ArrayList<Marks> getArrayMarks() throws SQLException {
        ArrayList<Marks> marks = new ArrayList<>();
        String select = "SELECT * FROM marks";
        ResultSet resultSet = db.getData(select);
        while (resultSet.next()) {
            int cw = resultSet.getInt("coursework");
            int ec = resultSet.getInt("economy");
            int erg = resultSet.getInt("ergaticSystem");
            int ns = resultSet.getInt("networks");
            int pg = resultSet.getInt("programming");
            int id = resultSet.getInt("id_student");
            marks.add(new Marks(cw, ec, ns, pg, erg, id));
        }
        return marks;
    }

    public void deleteAccount(String log) {
        String select = "DELETE FROM users" + " WHERE login = " + "\"" + log + "\"";
        db.updateData(select);
    }

    public void calculateStudent(Marks marks) {
        System.out.println(marks.getId_student());
        String select = "UPDATE scholarships SET ratio = 0 , scholarship = 0 WHERE id_students = " + marks.getId_student();
        double scholarship = ((double) marks.getCoursework() + marks.getEconomy() + marks.getNetworks() + marks.getProgramming() + marks.getErgaticSystem()) / 5;
        if (scholarship >= 5 && scholarship < 6) {
            select = "UPDATE scholarships SET ratio = 1 , scholarship = 1 * basicS WHERE id_students = " + marks.getId_student();
        }else if (scholarship >= 6 && scholarship < 8 ) {
            select = "UPDATE scholarships SET ratio = 1.2 ,  scholarship = 1.2 * basicS WHERE id_students = " + marks.getId_student();
        }else if (scholarship >= 8 && scholarship < 9 ) {
            select = "UPDATE scholarships SET ratio = 1.4 , scholarship = 1.4 * basicS WHERE id_students = " + marks.getId_student();
        }else if (scholarship >= 9 ) {
            select = "UPDATE scholarships SET ratio = 1.6 ,  scholarship = 1.6 * basicS WHERE id_students = " + marks.getId_student();
        }
        db.updateData(select);
    }

    public void updateScholarship() {
        String select = "UPDATE scholarships SET scholarship = ratio * basicS";
        db.updateData(select);
    }

    public void deleteStudent(int id) {
        String select = "DELETE FROM students" + " WHERE id = " + id;
        db.updateData(select);
    }

    public void deleteMarks(int id) {
        String select = "DELETE FROM marks" + " WHERE id_students = " + id;
        db.updateData(select);
    }

    public void deleteScholarship (int id) {
        String select = "DELETE FROM scholarships" + " WHERE id_students = " + id;
        db.updateData(select);
    }

    public void changeRole(String log, int newRole) {
        String select = "UPDATE users SET role = " + newRole + " WHERE login = " + "\"" + log + "\"";
        db.updateData(select);
    }

    public void changeAccess(String log) {
        String select = "UPDATE users SET access = 1" + " WHERE login = " + "\"" + log + "\"";
        db.updateData(select);
    }

    public void changeBasicScholarship(double basicS) {
        String select = "UPDATE scholarships SET basicS = " + basicS;
        db.updateData(select);
    }

    public void addScholarship(int id) {
        String insertFields = "INSERT INTO scholarships(basicS, ratio, scholarship, id_students) VALUES ('";
        String insertValues = 0 + "','" + 0 + "','" + 0 + "','" + id + "')";
        String insert = insertFields + insertValues;
        db.updateData(insert);
    }


    public void changeDataStudent(Students students) {
        String select = "UPDATE students SET surname = " + "\"" + students.getSurname() + "\"" + " , name = " + "\"" + students.getName() + "\"" + " , patronymic = "+ "\"" + students.getPatronymic() + "\""
                + " , date = " + students.getDate() + " WHERE id = " + students.getId();
        db.updateData(select);
    }

    public void changeDataMarks (Marks marks) {
        String select = "UPDATE marks SET coursework = " + "\"" + marks.getCoursework() + "\"" + " , economy = " + "\"" + marks.getEconomy() + "\"" + " , networks = " + marks.getNetworks()
                + " , programming = " + marks.getProgramming() + " , ergaticSystem = " + marks.getErgaticSystem() + " WHERE id_students = " + marks.getId_student();
        db.updateData(select);
    }

    public double getBasicScholarship() throws SQLException {
        double basicS = 0;
        String select = "SELECT basicS FROM scholarships";
        ResultSet resultSet = db.getData(select);
        if (resultSet.next()) {
            basicS = resultSet.getDouble("basicS");
        }
        return basicS;
    }

    public double getScholarship(int id) throws SQLException {
        double sc = 0;
        String select = "SELECT scholarship FROM scholarships WHERE id_students = " + id;
        ResultSet resultSet = db.getData(select);
        if (resultSet.next()) {
            sc = resultSet.getDouble("scholarship");
        }
        return sc;
    }

}

