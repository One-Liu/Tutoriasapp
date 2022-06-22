package uv.fei.tutorias.main;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uv.fei.tutorias.domain.ProblematicaAcademica;
import uv.fei.tutorias.utilidades.DatosGlobalesDeSesion;
import uv.fei.tutorias.utilidades.UtilidadVentana;

public class MenuPrincipalJefeDeCarreraControlador {
    @FXML
    private void clicConsultarReporteDeTutoriasAcademicas(ActionEvent evento) {
    }
    
    @FXML
    private void clicRegistrarSolucionAProblematicaAcademica(ActionEvent evento) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUIProblematicasSinSolucion.fxml"));
            Parent raiz = cargadorFXML.load();
            ProblematicasSinSolucionControlador controladorGUI = cargadorFXML.getController();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Probleamaticas academicas sin solucion");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException excepcionIO) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
        
    }
    
    @FXML
    private void clicConsultarSolucionAProblematicaAcademica(ActionEvent evento) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUISeleccionDeSolucionAProblematicaAcademica_ConsultarSolucion.fxml"));
            Parent raiz = cargadorFXML.load();
            SeleccionDeSolucionAProblematicaAcademica_ConsultarSolucionControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.cargarDatos();
            controladorGUI.cargarCamposGUI();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Seleccion de solución a problemática académica");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException excepcionIO) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        } catch(SQLException excepcionSQL) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }
    
    @FXML
    private void clicModificarSolucionAProblematicaAcademica(ActionEvent evento) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUISeleccionDeSolucionAProblematicaAcademica_ModificacionSolucion.fxml"));
            Parent raiz = cargadorFXML.load();
            SeleccionDeSolucionAProblematicaAcademica_ModificacionSolucionControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.cargarDatos();
            controladorGUI.cargarCamposGUI();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Seleccion de solución a problemática académica");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException excepcionIO) {
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
