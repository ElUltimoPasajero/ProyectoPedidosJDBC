package com.example.gestorpedidos;

import com.example.gestorpedidos.Item.Item;
import com.example.gestorpedidos.Item.ItemDAOImplement;
import com.example.gestorpedidos.Pedido.Pedido;
import com.example.gestorpedidos.Pedido.PedidoDAO;
import com.example.gestorpedidos.Pedido.PedidoDAOImplement;
import com.example.gestorpedidos.Producto.Producto;
import com.example.gestorpedidos.Producto.ProductoDAO;
import com.example.gestorpedidos.Producto.ProductoDAOImplement;
import com.example.gestorpedidos.User.User;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Controlador de la pantalla principal de la aplicación.
 */
public class MainScreenController {
    @javafx.fxml.FXML
    private Label lblInfoUser;
    @javafx.fxml.FXML
    private TableView<Pedido> table;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> columnCodigo;
    @javafx.fxml.FXML
    private TableColumn<Pedido, Date> columnFecha;
    @javafx.fxml.FXML
    private TableColumn<Pedido, Double> columnTotal;

    private Stage loginStage;
    @javafx.fxml.FXML
    private Button btnLogOut;

    /**
     * Establece la ventana de inicio de sesión.
     *
     * @param stage Instancia de Stage que representa la ventana de inicio de sesión.
     */
    public void setLoginStage(Stage stage) {
        this.loginStage = stage;
    }

    @javafx.fxml.FXML
    public void initialize() {
        // Obtiene el usuario actual
        User u = Session.getCurrentUser();
        // Inicializa las clases DAO para acceder a la base de datos
        PedidoDAOImplement pedidoDAO = new PedidoDAOImplement();
        ItemDAOImplement itemDao = new ItemDAOImplement();
        ProductoDAOImplement productoDao = new ProductoDAOImplement();

        // Obtiene la lista de pedidos del usuario
        List<Pedido> pedidos = pedidoDAO.getAllfromUser(u);

        // Configura las columnas de la tabla
        columnCodigo.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getCod()));
        columnFecha.setCellValueFactory(fila -> new SimpleObjectProperty<>(fila.getValue().getFecha()));
        columnTotal.setCellValueFactory(fila -> new SimpleObjectProperty<>(fila.getValue().getTotal()));

        // Muestra un mensaje de bienvenida con el nombre de usuario
        lblInfoUser.setText("Bienvenido a tus pedidos " + u.getUsername() + " !!!!");

        // Llena la tabla con los pedidos
        table.getItems().addAll(pedidos);

        // Imprime los códigos de pedido
        System.out.println(pedidoDAO.getCod());

        ArrayList<String> codigosPedido = new ArrayList<>();
        String codigoPed;
    }

    @javafx.fxml.FXML
    public void logOut(ActionEvent actionEvent) {
        // Cierra la sesión actual y vuelve a la pantalla de inicio de sesión
        Session.setCurrentUser(null);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login-view.fxml"));
        Parent root;
        try {
            root = loader.load();
            Stage stage = (Stage) btnLogOut.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void click(Event event) {
        // Maneja el evento de clic en un pedido para mostrar los detalles en la pantalla de ItemScreen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("item-Screen.fxml"));
        Parent root = null;
        Session.setSelectedPedido(table.getSelectionModel().getSelectedItem());

        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ItemScreenController itemScreenController = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        System.out.println("////////");
    }

    /**
     * Cierra la ventana de inicio de sesión si está abierta.
     */
    public void closeLoginWindow() {
        if (loginStage != null) {
            loginStage.close();
        }
    }
}


