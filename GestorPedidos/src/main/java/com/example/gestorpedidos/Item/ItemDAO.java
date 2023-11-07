package com.example.gestorpedidos.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Interfaz que define las operaciones para acceder y manipular objetos Item en la base de datos.
 */
public interface ItemDAO {

    /**
     * Obtiene una lista de ítems por su código de pedido.
     *
     * @param codigoPedido El código del pedido del que se desean obtener los ítems.
     * @return Una lista de ítems relacionados con el código de pedido especificado.
     */
    ArrayList<Item> getAllIds(String codigoPedido);

    // Puedes agregar más métodos para la gestión de ítems en la base de datos si es necesario.
}