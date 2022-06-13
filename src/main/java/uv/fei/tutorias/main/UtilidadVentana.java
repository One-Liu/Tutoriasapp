package uv.fei.tutorias.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Optional;

public class UtilidadVentana {
    
    private static Optional<ButtonType> option;
    
    public static void pasarValoresEntreVentanas(Object object,String direccionFxml, String tituloDeLaVentana,ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        URL url = Paths.get(direccionFxml).toUri().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Scene scene = new Scene(fxmlLoader.load(url ), 600, 400);
        stage.setUserData(object);
        stage.setTitle(tituloDeLaVentana);
        stage.setScene(scene);
        stage.show();
    }
    public static Object recuperarValoresDeLaVentana(Pane panel,Object objeto){
        Stage stage = (Stage) panel.getScene().getWindow();
        objeto = stage.getUserData();
        return objeto;
    }

    public static void mostrarAlertaSinConfirmacion(String titulo, String mensaje, Alert.AlertType tipoAlerta){
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    public static void mostrarAlertaConfirmacion(String titulo, String mensaje, Alert.AlertType tipoAlerta){
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        option = alerta.showAndWait();
    }

    public static Optional<ButtonType> getOption() {
        return option;
    }

    public static void mensajePerdidaDeConexion(){
        UtilidadVentana.mostrarAlertaSinConfirmacion(
                "Pérdida de conexión",
                "No se pudo conectar con la base de datos. "+
                        "Por favor, inténtelo más tarde.",
                Alert.AlertType.ERROR
        );
    }

    public static void mensajeErrorAlCargarLaInformacionDeLaVentana(){
        UtilidadVentana.mostrarAlertaSinConfirmacion("Error de sistema", "Hubo un error "
                + "al cargar la información. Por favor, inténtelo más tarde",Alert.AlertType.ERROR);
    }


}
