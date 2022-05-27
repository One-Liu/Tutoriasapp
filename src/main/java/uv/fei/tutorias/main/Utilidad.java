package uv.fei.tutorias.main;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

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

    public static void cerrarVentana(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
