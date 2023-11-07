package com.example.gestorpedidos.Producto;

import com.example.gestorpedidos.DB.DatabaseConnection;
import com.example.gestorpedidos.Pedido.Pedido;
import com.example.gestorpedidos.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase que implementa la interfaz ProductoDAO para acceder a los datos de los productos en la base de datos.
 */
public class ProductoDAOImplement implements ProductoDAO {

    /**
     * Obtiene el nombre de un producto por su identificador.
     *
     * @param productoId Identificador del producto
     * @return Nombre del producto
     */
    @Override
    public String getName(Integer productoId) {
        // Variable para almacenar el nombre del producto
        String gameName;
        // Consulta SQL para obtener el nombre del producto por su ID
        String query = "SELECT nombre FROM producto WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            // Establecer el ID del producto en la consulta
            statement.setInt(1, productoId);
            ResultSet resultSet = statement.executeQuery();

            // Obtener el nombre del juego
            gameName = resultSet.getString("nombre");
        } catch (SQLException e) {
            // Manejar excepciones de SQL
            throw new RuntimeException(e);
        }
        return gameName;
    }

    /**
     * Obtiene el precio de un producto por su identificador.
     *
     * @param productoId Identificador del producto
     * @return Precio del producto
     */
    @Override
    public double getPrice(Integer productoId) {
        // Variable para almacenar el precio del producto
        Double gamePrice;
        // Consulta SQL para obtener el precio del producto por su ID
        String query = "SELECT precio FROM producto WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            // Establecer el ID del producto en la consulta
            statement.setInt(1, productoId);
            ResultSet resultSet = statement.executeQuery();

            // Obtener el precio del juego
            gamePrice = resultSet.getDouble("precio");
        } catch (SQLException e) {
            // Manejar excepciones de SQL
            throw new RuntimeException(e);
        }

        return gamePrice;
    }

    /**
     * Obtiene un producto por su identificador.
     *
     * @param idGame Identificador del producto
     * @return Producto
     */
    @Override
    public Producto getProductoById(int idGame) {
        // Crear una instancia de Producto para almacenar la información
        Producto producto = new Producto();
        Connection connection;

        try {
            connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM producto WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idGame);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Establecer los atributos del producto a partir de los datos de la base de datos
                producto.setProductoName(resultSet.getString("nombre"));
                producto.setProductoId(resultSet.getInt("id"));
                producto.setProductoPrice(resultSet.getDouble("precio"));
                producto.setCantidad(resultSet.getInt("cantidad_disponible"));
            }

        } catch (SQLException e) {
            // Manejar excepciones de SQL
            throw new RuntimeException(e);
        }

        return producto;
    }
}