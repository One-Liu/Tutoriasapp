package uv.fei.tutorias.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import javafx.scene.Parent;
import uv.fei.tutorias.domain.Estudiante;
import uv.fei.tutorias.domain.Persona;

public class AplicacionDePrueba extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("GUILogin.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Aplicacion de prueba");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
