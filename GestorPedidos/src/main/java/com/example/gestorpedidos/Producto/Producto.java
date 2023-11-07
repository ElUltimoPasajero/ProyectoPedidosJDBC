package com.example.gestorpedidos.Producto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Producto implements Serializable {

    // Identificador único del producto
    private Integer productoId;

    // Nombre del producto
    private String productoName;

    // Precio del producto
    private double productoPrice;

    // Cantidad disponible del producto
    private Integer cantidad;
}