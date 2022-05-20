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
        URL url = Paths.get("src\\main\\resources\\uv.fei.tutorias.main\\GUIAsignarExperienciaEducativa-view.fxml").toUri().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Scene scene = new Scene(fxmlLoader.load(url ), 600, 600);
        primaryStage.setTitle("Experiencias educativas");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
