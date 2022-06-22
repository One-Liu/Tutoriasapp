package uv.fei.tutorias.utilidades;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.util.Optional;

public class UtilidadVentana {
    
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

    public static void cerrarVentana(ActionEvent evento) {
        Node fuente = (Node) evento.getSource();
        Stage escenario = (Stage) fuente.getScene().getWindow();
        escenario.close();
    }
}
