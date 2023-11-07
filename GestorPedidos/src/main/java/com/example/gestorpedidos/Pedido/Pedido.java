package com.example.gestorpedidos.Pedido;

import com.example.gestorpedidos.Item.Item;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase que representa un pedido realizado por un usuario.
 */
@Data
public class Pedido implements Serializable {

    private int id; // Identificador único del pedido.
    private Date fecha; // Fecha en la que se realizó el pedido.
    private String cod; // Código o identificador del pedido.
    private int userId; // Identificador del usuario que realizó el pedido.
    private double total; // Total del pedido, que puede representar el valor monetario total.

    // Añade getters y setters generados automáticamente por Lombok.
}