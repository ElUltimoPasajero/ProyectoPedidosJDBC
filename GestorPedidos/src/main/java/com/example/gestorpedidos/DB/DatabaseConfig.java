package com.example.gestorpedidos.DB;

import java.io.IOException;
import java.util.Properties;

/**
 * Esta clase proporciona métodos para obtener la configuración de la base de datos desde un archivo properties.
 */
public class DatabaseConfig {
    private static Properties properties = new Properties();

    static {
        try {
            // Cargamos el archivo de propiedades desde el classpath
            ClassLoader classLoader = DatabaseConfig.class.getClassLoader();
            properties.load(classLoader.getResourceAsStream("database.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene la URL de la base de datos desde el archivo de configuración.
     *
     * @return URL de la base de datos.
     */
    public static String getDatabaseURL() {
        return properties.getProperty("url");
    }

    /**
     * Obtiene el nombre de usuario de la base de datos desde el archivo de configuración.
     *
     * @return Nombre de usuario de la base de datos.
     */
    public static String getDatabaseUsername() {
        return properties.getProperty("user");
    }

    /**
     * Obtiene la contraseña de la base de datos desde el archivo de configuración.
     *
     * @return Contraseña de la base de datos.
     */
    public static String getDatabasePassword() {
        return properties.getProperty("pass");
    }
}