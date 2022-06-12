package uv.fei.tutorias.main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uv.fei.tutorias.bussinesslogic.SesionDeTutoriaAcademicaDAO;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

public class SeleccionDeSesionDeTutoriaControlador implements Initializable {
    @FXML
    private ComboBox<SesionDeTutoriaAcademica> cbFechasDeSesionDeTutoria;
    
    private final SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDAO = new SesionDeTutoriaAcademicaDAO();
    
    private ObservableList<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademica = FXCollections.observableArrayList();
    
    private void cargarSesionesDeTutoriaAcademica() throws SQLException {
        this.sesionesDeTutoriaAcademica = sesionDeTutoriaAcademicaDAO.obtenerSesionesDeTutoriaAcademica();
    }
    
    private void cargarCamposGUI() {
        try {
            cargarSesionesDeTutoriaAcademica();
            this.cbFechasDeSesionDeTutoria.setItems(sesionesDeTutoriaAcademica);
        } catch(SQLException ex) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCamposGUI();
    }    
    
    @FXML
    private void clicCancelar(ActionEvent event) {
        UtilidadVentana.cerrarVentana(event);
    }

    @FXML
    private void clicSeleccionar(ActionEvent event) {
        SesionDeTutoriaAcademica sesionDeTutoriaAcademicaSeleccionada = this.cbFechasDeSesionDeTutoria.getSelectionModel().getSelectedItem();
        
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("/src/main/resources/uv/fei/tutorias/main/GUIRegistroDeHorarioDeSesionDeTutoria.fxml"));
            Parent raiz = cargadorFXML.load();
            RegistroDeHorarioDeSesionDeTutoriaControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.setSesionDeTutoriaAcademica(sesionDeTutoriaAcademicaSeleccionada);
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Registro de horario de sesión de tutoría");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException ioException) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }

}
