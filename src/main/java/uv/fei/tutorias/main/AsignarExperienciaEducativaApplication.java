package uv.fei.tutorias.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class AsignarExperienciaEducativaApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(AplicacionDePrueba.class.getResource("GUILogin.fxml"));
        System.out.println(AplicacionDePrueba.class.getResource("GUILogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 870, 530);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
