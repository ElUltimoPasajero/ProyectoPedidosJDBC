module com.example.gestorpedidos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;


    opens com.example.gestorpedidos to javafx.fxml;
    exports com.example.gestorpedidos;
    exports com.example.gestorpedidos.DB;
    opens com.example.gestorpedidos.DB to javafx.fxml;
    exports com.example.gestorpedidos.Producto;
    opens com.example.gestorpedidos.Producto to javafx.fxml;
}