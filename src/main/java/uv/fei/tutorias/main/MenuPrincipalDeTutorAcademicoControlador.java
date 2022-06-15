package uv.fei.tutorias.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import uv.fei.tutorias.domain.TutorAcademico;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uv.fei.tutorias.domain.DatosGlobalesDeSesion;

public class MenuPrincipalDeTutorAcademicoControlador {    
    @FXML
    private void clicLlenarReporteDeTutoriasAcademicas(ActionEvent actionEvent) {
        TutorAcademico tutorAcademico = new TutorAcademico();
        System.out.println(tutorAcademico);
//        Utilidad.pasarValoresEntreVentanas(tutorAcademico,"src\\main\\resources\\uv.fei.tutorias.main\\GUILlenarReporteDeTutoria.fxml","LLenar reporte de tutoria",actionEvent);

    }

    @FXML
    private void clicConsultarProblematicaAcademica(ActionEvent actionEvent) {
    }
    
    @FXML
    private void clicRegistrarHorariosDeSesionDeTutoria() {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("/src/main/resources/uv/fei/tutorias/main/GUISeleccionDeSesionDeTutoria.fxml"));
            Parent raiz = cargadorFXML.load();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Selección de sesión de tutoría");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException ioException) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
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
