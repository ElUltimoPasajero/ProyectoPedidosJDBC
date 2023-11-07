package com.example.gestorpedidos.User;

import com.example.gestorpedidos.DB.DatabaseConnection;
import com.example.gestorpedidos.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImplement implements UserDAO {


    @Override
    public ArrayList<User> getAll() {
        return null;
    }

    @Override
    public User get() {
        return null;
    }

    @Override
    public User validateUser(String username, String password) {
        User result = null;
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM usuario WHERE nombre = ? AND contraseña = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                result = new User();
                result.setUsername(resultSet.getString("nombre"));
                result.setPassword(resultSet.getString("contraseña"));
                result.setId(resultSet.getInt("id"));
                result.setEmail(resultSet.getString("email"));



                Session.setCurrentUser(result);





            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}


