package uv.fei.tutorias.main;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uv.fei.tutorias.domain.DatosGlobalesDeSesion;

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
        } catch(IOException excepcionIO) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }

    @FXML
    private void clicModificarFechasDeSesionDeTutoria(ActionEvent evento) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUISeleccionDePeriodoEscolar.fxml"));
            Parent raiz = cargadorFXML.load();
            SeleccionDePeriodoEscolarControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.cargarCamposGUI();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Selección de periodo escolar");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException excepcionIO) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }

    @FXML
    private void clicRegistrarUsuario(ActionEvent evento) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUISignUp.fxml"));
            Parent raiz = cargadorFXML.load();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Sign up");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException excepcionIO) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }

    @FXML
    private void clicAsignarTutorAEstudiante(ActionEvent evento) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUIAsignacionDeTutorAcademico.fxml"));
            Parent raiz = cargadorFXML.load();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Asignación de tutor académico");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException excepcionIO) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }
    
    @FXML
    private void clicModificarAsignacionDeTutor(ActionEvent evento) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUISeleccionDeEstudiante.fxml"));
            Parent raiz = cargadorFXML.load();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Selección de estudiante");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException excepcionIO) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }
    
    @FXML
    private void clicCerrarSesion(ActionEvent evento) {
        DatosGlobalesDeSesion.getDatosGlobalesDeSesion().setCoordinador(null);
        UtilidadVentana.cerrarVentana(evento);
        
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUILogin.fxml"));
            Parent raiz = cargadorFXML.load();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Login");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException excepcionIO) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }
}
