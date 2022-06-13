package uv.fei.tutorias.main;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuPrincipalCoordinadorDeTutoriasControlador {
    @FXML
    private void clicConsultarReporteGeneralDeTutoriasAcademicas(ActionEvent actionEvent) {

    }

    @FXML
    private void clicRegistrarFechasDeSesionDeTutoria(ActionEvent actionEvent) {

    }

    @FXML
    private void clicRegistrarFechasDeCierreParaLaEntregaDeReporte(ActionEvent actionEvent) {
    }

    @FXML
    private void clicConsultarOfertaAcademica(ActionEvent actionEvent) {
    }
    
    @FXML
    private void clicConsultarReportePorTutorAcademico(ActionEvent evento) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("/src/main/resources/uv/fei/tutorias/main/GUISeleccionDeReporte.fxml"));
            Parent raiz = cargadorFXML.load();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Selección de reporte");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException ioException) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }

    @FXML
    private void clicModificarFechasDeSesionDeTutoria(ActionEvent evento) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("/src/main/resources/uv/fei/tutorias/main/GUISeleccionDePeriodoEscolar.fxml"));
            Parent raiz = cargadorFXML.load();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Selección de periodo escolar");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException ioException) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }

    @FXML
    private void clicRegistrarUsuario(ActionEvent evento) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("/src/main/resources/uv/fei/tutorias/main/GUISignUp.fxml"));
            Parent raiz = cargadorFXML.load();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Sign up");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException ioException) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }

    @FXML
    private void clicAsignarTutorAEstudiante(ActionEvent evento) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("/src/main/resources/uv/fei/tutorias/main/GUIAsignacionDeTutorAcademico.fxml"));
            Parent raiz = cargadorFXML.load();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Asignación de tutor académico");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException ioException) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }
    
    @FXML
    private void clicModificarAsignacionDeTutor(ActionEvent evento) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("/src/main/resources/uv/fei/tutorias/main/GUIModificacionDeAsignacionDeTutorAcademico.fxml"));
            Parent raiz = cargadorFXML.load();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Modificación de asignación de tutor académico");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException ioException) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }
}
