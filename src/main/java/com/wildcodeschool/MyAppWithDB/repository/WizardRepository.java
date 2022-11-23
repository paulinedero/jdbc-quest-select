package com.wildcodeschool.MyAppWithDB.repository;

import com.wildcodeschool.MyAppWithDB.entity.Wizard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WizardRepository {
    private final static String DB_URL = "jdbc:mysql://localhost:3306/spring_jdbc_quest?serverTimezone=GMT";
    private final static String DB_USER = "root";
    private final static String DB_PASSWORD = "root";

    public List<Wizard> findAll() {

        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM wizard;"
            );

            ResultSet resultSet = statement.executeQuery();

            //Result as Object
            List<Wizard> wizards = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String birthday = resultSet.getString("birthday");
                String birthPlace = resultSet.getString("birth_place");
                String biography = resultSet.getString("biography");
                boolean isMuggle = resultSet.getString("is_muggle").equals("1");

                wizards.add(new Wizard(id, firstName, lastName, birthday, birthPlace, biography, isMuggle));
            }

            return wizards;

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Wizard> findByLastName(String lastName) {
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM wizard WHERE last_name = ?;"
            );

            statement.setString(1, lastName);

            ResultSet resultSet = statement.executeQuery();

            //Result as Object
            List<Wizard> wizards = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String wizardLastName = resultSet.getString("last_name");
                String birthday = resultSet.getString("birthday");
                String birthPlace = resultSet.getString("birth_place");
                String biography = resultSet.getString("biography");
                boolean isMuggle = resultSet.getString("is_muggle").equals("1");

                wizards.add(new Wizard(id, firstName, wizardLastName, birthday, birthPlace, biography, isMuggle));
            }
            return wizards;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
