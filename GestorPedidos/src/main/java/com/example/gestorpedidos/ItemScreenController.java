package com.example.gestorpedidos;

import com.example.gestorpedidos.Item.Item;
import com.example.gestorpedidos.Item.ItemDAO;
import com.example.gestorpedidos.Item.ItemDAOImplement;
import com.example.gestorpedidos.Pedido.Pedido;
import com.example.gestorpedidos.Pedido.PedidoDAOImplement;
import com.example.gestorpedidos.Producto.Producto;
import com.example.gestorpedidos.Producto.ProductoDAO;
import com.example.gestorpedidos.Producto.ProductoDAOImplement;
import com.example.gestorpedidos.User.User;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemScreenController {
    @FXML
    public TableColumn<Item, Integer> columnAmmount;
    @FXML
    public TableColumn<Item, String> columnItemName;
    @FXML
    private TableView<Item> tableItem;

    @FXML
    private TableColumn<Item, Double> columnPrice;
    @FXML
    private Button btnLogOut;
    private static Stage itemScreenStage;

    /**
     * Establece la ventana de la pantalla de elementos (ítems).
     *
     * @param stage Instancia de Stage que representa la ventana de la pantalla de elementos.
     */
    public void setItemScreenStage(Stage stage) {
        itemScreenStage = stage;
    }

    /**
     * Inicializa la pantalla de elementos (ítems) con los elementos correspondientes.
     */
    public void initialize() {
        // Obtiene el pedido seleccionado de la sesión
        Pedido p = Session.getSelectedPedido();
        ItemDAOImplement itemDao = new ItemDAOImplement();
        ProductoDAOImplement productoDao = new ProductoDAOImplement();

        // Obtiene los ítems del pedido
        ArrayList<Item> items = itemDao.getAllIds(p.getCod());

        // Imprime los ítems en la consola (puedes eliminar esta parte)
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
        }

        // Crea una lista observable de ítems
        ObservableList<Item> obItems = FXCollections.observableArrayList(items);

        // Asigna las propiedades de las columnas a los atributos de los ítems
        columnAmmount.setCellValueFactory(fila -> new SimpleObjectProperty<>(fila.getValue().getCantidad()));
        columnPrice.setCellValueFactory(fila -> new SimpleObjectProperty<>(fila.getValue().getProducto().getProductoPrice()));
        columnItemName.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getProducto().getProductoName()));

        // Agrega los ítems a la tabla
        tableItem.getItems().addAll(obItems);
    }

    /**
     * Maneja el evento de cierre de sesión (logout).
     *
     * @param actionEvent Evento de acción (pulsar el botón de logout)
     */
    @FXML
    public void LogOut(ActionEvent actionEvent) {
        // Cierra la sesión actual
        Session.setCurrentUser(null);

        // Cierra la ventana de la pantalla de elementos (ítems)
        if (itemScreenStage != null) {
            itemScreenStage.close();
        }
        // Agrega aquí otras ventanas que quieras cerrar al hacer logout

        // Carga la ventana de inicio de sesión (login)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login-view.fxml"));
        Parent root;
        try {
            root = loader.load();
            Stage stage = (Stage) btnLogOut.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cierra la ventana de la pantalla de elementos (ítems).
     */
    public void closeLoginWindow() {
        if (itemScreenStage != null) {
            itemScreenStage.close();
        }
    }
}