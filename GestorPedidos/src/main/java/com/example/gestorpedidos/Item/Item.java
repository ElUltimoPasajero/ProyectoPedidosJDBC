package com.example.gestorpedidos.Item;

import com.example.gestorpedidos.Producto.Producto;
import lombok.Data;

import java.io.Serializable;

/**
 * Clase que representa un elemento (ítem) de un pedido.
 */
@Data
public class Item implements Serializable {

    private Integer id;           // Identificador único del ítem
    private String codigoPedido;  // Código del pedido al que pertenece el ítem
    private Integer cantidad;     // Cantidad de productos en el ítem
    private Integer producto_id;  // Identificador del producto asociado al ítem
    private Producto producto;    // Producto asociado al ítem

    // Getters y setters generados automáticamente por Lombok
}