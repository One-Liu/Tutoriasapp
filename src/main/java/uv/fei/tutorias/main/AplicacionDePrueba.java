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
        //FXMLLoader cargadorFXML = new FXMLLoader(this.getClass().getResource("GUIModificacionDeAsignacionDeTutorAcademico.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("GUISeleccionDeReporte.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("GUISeleccionDeEstudiante.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        Scene scene = new Scene(fxmlLoader.load());
        
        //Parent raiz = cargadorFXML.load();
        //ModificacionDeAsignacionDeTutorAcademicoControlador controladorGUI = cargadorFXML.getController();
        //controladorGUI.setEstudiante(new Estudiante(1,"S20015728",false,2,new Persona("JOSHUA ELIUD","HERNÁNDEZ","SUÁREZ",4)));
        //controladorGUI.cargarCamposGUI();
        //Scene scene = new Scene(raiz);
        
        primaryStage.setTitle("Aplicacion de prueba");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
