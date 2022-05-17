package uv.fei.tutorias.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public class LoginApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL url = Paths.get("src\\main\\resources\\uv.fei.tutorias.main\\GUILogin.fxml").toUri().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Scene scene = new Scene(FXMLLoader.load(url ), 600, 400);
        primaryStage.setTitle("LOGIN");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
