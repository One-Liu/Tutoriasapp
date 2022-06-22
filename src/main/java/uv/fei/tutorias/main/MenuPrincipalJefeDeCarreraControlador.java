package uv.fei.tutorias.main;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uv.fei.tutorias.utilidades.DatosGlobalesDeSesion;
import uv.fei.tutorias.utilidades.UtilidadVentana;

public class MenuPrincipalJefeDeCarreraControlador {
    @FXML
    private void clicConsultarReporteDeTutoriasAcademicas(ActionEvent evento) {
    }
    
    @FXML
    private void clicRegistrarSolucionAProblematicaAcademica(ActionEvent evento) {
    }
    
    @FXML
    private void clicConsultarSolucionAProblematicaAcademica(ActionEvent evento) {
    }
    
    @FXML
    private void clicModificarSolucionAProblematicaAcademica(ActionEvent evento) {
    }
    
    @FXML
    private void clicConsultarOfertaAcademica(ActionEvent evento) {
    }
    
    @FXML
    private void clicCerrarSesion(ActionEvent evento) {
        DatosGlobalesDeSesion.getDatosGlobalesDeSesion().setJefeDeCarrera(null);
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
