package uv.fei.tutorias.main;

import uv.fei.tutorias.utilidades.UtilidadVentana;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import uv.fei.tutorias.domain.TutorAcademico;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uv.fei.tutorias.utilidades.DatosGlobalesDeSesion;

public class MenuPrincipalDeTutorAcademicoControlador {    
    @FXML
    private void clicLlenarReporteDeTutoriasAcademicas(ActionEvent evento) {
        TutorAcademico tutorAcademico = new TutorAcademico();
        System.out.println(tutorAcademico);
//        Utilidad.pasarValoresEntreVentanas(tutorAcademico,"src\\main\\resources\\uv.fei.tutorias.main\\GUILlenarReporteDeTutoria.fxml","LLenar reporte de tutoria",actionEvent);

    }
    
    @FXML
    private void clicRegistrarProblematicaAcademica(ActionEvent evento) {
    }

    @FXML
    private void clicConsultarProblematicaAcademica(ActionEvent evento) {
    }
    
    @FXML
    private void clicModificarProblematicaAcademica(ActionEvent evento) {
    }
    
    @FXML
    private void clicEliminarProblematicaAcademica(ActionEvent evento) {
    }
    
    @FXML
    private void clicRegistrarHorariosDeSesionDeTutoria(ActionEvent evento) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUISeleccionDeSesionDeTutoria_RegistroDeHorarioDeSesionDeTutoria.fxml"));
            Parent raiz = cargadorFXML.load();
            SeleccionDeSesionDeTutoria_RegistroDeHorarioDeSesionDeTutoriaControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.cargarDatos();
            controladorGUI.cargarCamposGUI();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Selección de sesión de tutoría");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException ioException) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        } catch(SQLException excepcionSQL) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }
    
    @FXML
    private void clicConsultarOfertaAcademica(ActionEvent evento) {
    }
    
    @FXML
    private void clicCerrarSesion(ActionEvent evento) {
        DatosGlobalesDeSesion.getDatosGlobalesDeSesion().setTutorAcademico(null);
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
