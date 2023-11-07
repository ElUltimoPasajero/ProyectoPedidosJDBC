package com.example.gestorpedidos;

import com.example.gestorpedidos.Item.ItemDAOImplement;
import com.example.gestorpedidos.Pedido.Pedido;
import com.example.gestorpedidos.Pedido.PedidoDAOImplement;
import com.example.gestorpedidos.User.User;
import com.example.gestorpedidos.User.UserDAOImplement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador de la pantalla de inicio de sesión en la aplicación.
 */
public class LoginController {
    @FXML
    private Label welcomeText;
    @FXML
    private BorderPane borderPane;
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Label info;
    private static Stage loginStage;

    /**
     * Inicia la ventana de inicio de sesión.
     *
     * @param stage Instancia de Stage que representa la ventana de inicio de sesión.
     */
    public void start(Stage stage) {
        loginStage = stage;
    }

    /**
     * Obtiene la instancia de la ventana de inicio de sesión.
     *
     * @return Instancia de Stage que representa la ventana de inicio de sesión.
     */
    public static Stage getLoginStage() {
        return loginStage;
    }

    /**
     * Maneja el evento de inicio de sesión (login).
     *
     * @param actionEvent Evento de acción (pulsar el botón de inicio de sesión).
     */
    @FXML
    public void btnLogin(ActionEvent actionEvent) {
        // Obtiene el nombre de usuario y contraseña ingresados
        String user = txtUser.getText();
        String pass = txtPassword.getText();

        if (user.length() < 4 || pass.length() < 4) {
            info.setText("Introduce los datos");
            info.setStyle("-fx-background-color:red;-fx-text-fill: white");
        } else {
            // Valida al usuario
            User u = new UserDAOImplement().validateUser(user, pass);
            if (u == null) {
                info.setText("Usuario no encontrado");
                info.setStyle("-fx-background-color:red;-fx-text-fill: white");
            } else {
                info.setText("Usuario " + user + "( " + pass + ") correcto ");
                info.setStyle("-fx-background-color:green;-fx-text-fill: white");

                // Establece el usuario actual en la sesión
                Session.setCurrentUser(u);

                // Imprime información sobre el usuario
                System.out.println("Usuario: " + u.getUsername());
                System.out.println("Email: " + u.getEmail());
                System.out.println("ID: " + u.getId());

                try {
                    // Carga la ventana principal
                    FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("mainScreen.fxml"));
                    Scene mainScreenScene = new Scene(mainScreenLoader.load());
                    Stage mainScreenStage = new Stage();
                    mainScreenStage.setScene(mainScreenScene);

                    MainScreenController mainScreenController = mainScreenLoader.getController();

                    mainScreenStage.show();

                    // Cierra la ventana de inicio de sesión
                    ((Stage) btnLogin.getScene().getWindow()).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}