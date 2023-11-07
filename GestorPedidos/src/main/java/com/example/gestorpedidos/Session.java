package com.example.gestorpedidos;

import com.example.gestorpedidos.Pedido.Pedido;
import com.example.gestorpedidos.User.User;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase de sesión que almacena la información del usuario actual y el pedido seleccionado.
 */
public class Session {

    /**
     * El usuario actual que ha iniciado sesión.
     */
    @Setter
    @Getter
    private static User currentUser;

    /**
     * El pedido seleccionado en la aplicación.
     */
    @Setter
    @Getter
    private static Pedido selectedPedido;
}