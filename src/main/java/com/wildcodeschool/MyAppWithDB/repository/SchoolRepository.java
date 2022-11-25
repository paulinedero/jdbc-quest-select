package com.wildcodeschool.MyAppWithDB.repository;

import com.wildcodeschool.MyAppWithDB.entity.School;
import com.wildcodeschool.MyAppWithDB.entity.Wizard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SchoolRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/spring_jdbc_quest?serverTimezone=GMT";
    private final static String DB_USER = "root";
    private final static String DB_PASSWORD = "root";

    public List<School> findAll() {

        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM school;"
            );

            ResultSet resultSet = statement.executeQuery();

            //Result as Object
            List<School> schools = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int capacity = resultSet.getInt("capacity");
                String country = resultSet.getString("country");

                schools.add(new School(id, name, capacity, country));
            }

            return schools;

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<School> findSchoolById(String id) {

        try {
            int schoolId = Integer.parseInt(id);

            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM school WHERE id=?;"
            );

            statement.setInt(1, schoolId);

            System.out.println(statement);

            ResultSet resultSet = statement.executeQuery();

            List<School> schools = new ArrayList<>();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int capacity = resultSet.getInt("capacity");
                String schoolCountry = resultSet.getString("country");

                schools.add(new School(schoolId, name, capacity, schoolCountry));
            }

            return schools;

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<School> findSchoolsByCountry(String country) {

        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM school WHERE country=?;"
            );

            statement.setString(1, country);

            ResultSet resultSet = statement.executeQuery();

            //Result as Object
            List<School> schools = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int capacity = resultSet.getInt("capacity");
                String schoolCountry = resultSet.getString("country");

                schools.add(new School(id, name, capacity, schoolCountry));
            }

            return schools;

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public School save(String name, int capacity, String country) {

        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO school (name, capacity, country) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, name);
            statement.setInt(2, capacity);
            statement.setString(3, country);

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to insert data");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                return new School(id, name, capacity, country);
            } else {
                throw new SQLException("failed to get inserted id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}