
package uv.fei.tutorias.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class App {
    public static void main(String[] args) {
        Application.launch(OKButton.class, args);
    }

    public static class OKButton extends Application {

        @Override
        public void start(Stage stage) {
            Button btn = new Button("OK");
            Scene scene = new Scene(btn, 200, 250);
            stage.setScene(scene);
            stage.show();
        }
    }
}