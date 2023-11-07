package com.example.gestorpedidos.Pedido;

import com.example.gestorpedidos.DB.DatabaseConnection;
import com.example.gestorpedidos.Session;
import com.example.gestorpedidos.User.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PedidoDAOImplement implements PedidoDAO {


    @Override
    public ArrayList<Pedido> gettAll() {
        return null;
    }
    /**
     * Obtiene una lista de pedidos relacionados con un usuario específico.
     *
     * @param u El usuario para el que se recuperarán los pedidos.
     * @return Una lista de objetos Pedido relacionados con el usuario.
     */
    public ArrayList<Pedido> getAllfromUser(User u) {
        ArrayList<Pedido> results = new ArrayList<>();
        Connection connection;

        try {
            connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM pedido WHERE usuario_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Session.getCurrentUser().getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(resultSet.getInt("id"));
                pedido.setFecha(resultSet.getDate("fecha"));
                pedido.setCod(resultSet.getString("código"));
                pedido.setTotal(resultSet.getDouble("total"));
                results.add(pedido);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return results;
    }
    /**
     * Obtiene una lista de códigos de pedidos relacionados con un usuario específico.
     *
     * @return Una lista de cadenas que representan los códigos de los pedidos del usuario.
     */
    @Override
    public ArrayList<String> getCod() {
        Connection connection;
        ArrayList<String> codPed = new ArrayList<>();

        try {
            connection = DatabaseConnection.getConnection();
            String query = "SELECT código FROM pedido WHERE usuario_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Session.getCurrentUser().getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String codigoPedido = resultSet.getString("código");
                codPed.add(codigoPedido); // Agregar el código de pedido a la lista
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return codPed;
    }

}
