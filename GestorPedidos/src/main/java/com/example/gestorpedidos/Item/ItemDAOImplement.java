package com.example.gestorpedidos.Item;

import com.example.gestorpedidos.DB.DatabaseConnection;
import com.example.gestorpedidos.Producto.ProductoDAOImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implementación de la interfaz ItemDAO para acceder y manipular objetos Item en la base de datos.
 */
public class ItemDAOImplement implements ItemDAO {

    /**
     * Obtiene una lista de ítems por su código de pedido.
     *
     * @param codigoPedido El código del pedido del que se desean obtener los ítems.
     * @return Una lista de ítems relacionados con el código de pedido especificado.
     */
    public ArrayList<Item> getAllIds(String codigoPedido) {
        ArrayList<Item> items = new ArrayList<>();
        String query = "SELECT * FROM item WHERE codigo_pedido = ?";

        // Crear una instancia de ProductoDAOImplement para obtener productos relacionados con los ítems.
        ProductoDAOImplement productoDao = new ProductoDAOImplement();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, codigoPedido);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setCodigoPedido(resultSet.getString("codigo_pedido"));
                item.setCantidad(resultSet.getInt("cantidad"));

                // Obtener el producto relacionado con este ítem.
                item.setProducto(productoDao.getProductoById(resultSet.getInt("producto_id")));

                items.add(item);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener ítems por código de pedido.", e);
        }
        return items;
    }
}