package uv.fei.tutorias.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import uv.fei.tutorias.domain.ExperienciaEducativa;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Optional;

public class Utilidad {
    private static Optional<ButtonType> option;



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
        Utilidad.mostrarAlertaSinConfirmacion(
                "Pérdida de conexión",
                "No se pudo conectar con la base de datos. "+
                        "Por favor, inténtelo más tarde.",
                Alert.AlertType.ERROR
        );
    }

    public static void mensajeErrorAlCargarLaInformacionDeLaVentana(){
        Utilidad.mostrarAlertaSinConfirmacion("Error de sistema", "Hubo un error "
                + "al cargar la información. Por favor, inténtelo más tarde",Alert.AlertType.ERROR);
    }


}
