package com.wildcodeschool.MyAppWithDB.repository;

import com.wildcodeschool.MyAppWithDB.entity.School;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SchoolRepository {
    private final static String DB_URL = "jdbc:mysql://localhost:3306/spring_jdbc_quest?serverTimezone=GMT";
    private final static String DB_USER = "root";
    private final static String DB_PASSWORD = "root";

    /**
     * Find all schools
     *
     * @return List<School>
     */
    public List<School> findAllSchools() {
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM school;"
            );

            ResultSet resultSet = statement.executeQuery();

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

    /**
     * Find school by id
     *
     * @param id int
     * @return School
     */
    public School findSchoolById(int id) {
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM school WHERE id=?;"
            );

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            List<School> schools = new ArrayList<>();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int capacity = resultSet.getInt("capacity");
                String schoolCountry = resultSet.getString("country");

                return new School(id, name, capacity, schoolCountry);

            } else {
                throw new SQLException("No school with id " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Find schools by country
     *
     * @param country String
     * @return List<School>
     */
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

    /**
     * Create new school in database
     *
     * @param name String
     * @param capacity int
     * @param country String
     * @return School
     */
    public School saveNewSchool(String name, int capacity, String country) {
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

    /**
     * Update School in database
     *
     * @param id int
     * @param name String
     * @param capacity int
     * @param country String
     * @return School
     */
    public School updateSchool(int id, String name, int capacity, String country) {
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );

            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE school SET name=?, capacity=?, country=? WHERE id=?"
            );

            statement.setString(1, name);
            statement.setInt(2, capacity);
            statement.setString(3, country);
            statement.setInt(4, id);

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to update data");
            }
            return new School(id, name, capacity, country);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}