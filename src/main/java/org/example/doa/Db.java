package org.example.doa;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Db {
    private final  String url="jdbc:postgresql://localhost:5432/postgres";
    private  final  String user="postgres";
    private final   String password="12345";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
    public int getUsersCount(){
        String SQL="Select count (*) from student";
        int count=0;
        try {
            Connection conn=connect();
            Statement statement=conn.createStatement();
            ResultSet rs= statement.executeQuery(SQL);{
                rs.next();
                count=rs.getInt(1);
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    public  void addUser(String name, String surname,int age) {
        String SQL = "insert into student (name_student,surname,age) values (?,?, ?)";
        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setInt(3,age);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public Collection<? extends String> printUsers() {
        ArrayList <String> arrayList=new ArrayList<>();
        String SQL = "SELECT * FROM student ";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {


                arrayList.add(rs.getString("name_student") + "| "
                        + rs.getString("surname") +
                        " |" + rs.getInt("age"));}
                System.out.println(arrayList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }



}
