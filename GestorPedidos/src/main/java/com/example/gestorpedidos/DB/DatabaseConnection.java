package com.example.gestorpedidos.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Esta clase gestiona la conexión a la base de datos.
 */
public class DatabaseConnection {
    private static Connection connect = null;

    /**
     * Obtiene una conexión a la base de datos utilizando la configuración proporcionada en DatabaseConfig.
     *
     * @return La conexión a la base de datos.
     * @throws RuntimeException Si ocurre un error al establecer la conexión.
     */
    public static Connection getConnection() {
        try {
            if (connect == null || connect.isClosed()) {
                connect = DriverManager.getConnection(DatabaseConfig.getDatabaseURL(), DatabaseConfig.getDatabaseUsername(), DatabaseConfig.getDatabasePassword());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al establecer la conexión", e);
        }
        return connect;
    }

    /**
     * Cierra la conexión a la base de datos si está abierta.
     */
    public static void closeConnection() {
        if (connect != null) {
            try {
                connect.close();
            } catch (SQLException e) {
                // Manejar la excepción o registrarla, pero no interrumpir la ejecución
                e.printStackTrace();
            }
        }
    }
}