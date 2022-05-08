package uv.fei.tutorias.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class LoginController {
    @FXML
    private TextField txtcorreoInstitucional;
    @FXML
    private TextField txtContrasena;
    @FXML
    private Button btnIngresar;
    @FXML
    private Button btnRegistrar;

    public void onRegistrarseBtn(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        URL url = Paths.get("src\\main\\resources\\uv.fei.tutorias.main\\signUp-view.fxml").toUri().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Scene scene = new Scene(fxmlLoader.load(url ), 600, 400);
        stage.setTitle("Sign-up");
        stage.setScene(scene);
        stage.show();
        //cerramos la ventana de login
        Stage myStage = (Stage) this.btnRegistrar.getScene().getWindow();
        myStage.close();
    }

    public void onIngresarBtn(ActionEvent actionEvent) {
    }
}
