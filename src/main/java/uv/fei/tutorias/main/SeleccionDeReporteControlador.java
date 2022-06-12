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
import uv.fei.tutorias.bussinesslogic.TutorAcademicoDAO;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;
import uv.fei.tutorias.domain.TutorAcademico;

public class SeleccionDeReporteControlador implements Initializable {
    @FXML
    private ComboBox<TutorAcademico> cbTutoresAcademicos;
    @FXML
    private ComboBox<SesionDeTutoriaAcademica> cbFechasDeSesionDeTutoriaAcademica;
    
    private final TutorAcademicoDAO tutorAcademicoDAO = new TutorAcademicoDAO();
    private final SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDAO = new SesionDeTutoriaAcademicaDAO();
    
    private ObservableList<TutorAcademico> tutoresAcademicos = FXCollections.observableArrayList();
    private ObservableList<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademica = FXCollections.observableArrayList();
    
    private void cargarTutoresAcademicos() throws SQLException {
        this.tutoresAcademicos = tutorAcademicoDAO.obtenerTutoresAcademicos();
    }
    
    private void cargarSesionesDeTutoriaAcademica() throws SQLException {
        this.sesionesDeTutoriaAcademica = sesionDeTutoriaAcademicaDAO.obtenerSesionesDeTutoriaAcademica();
    }
    
    private void cargarCamposGUI() {
        try {
            cargarTutoresAcademicos();
            cargarSesionesDeTutoriaAcademica();
            this.cbTutoresAcademicos.setItems(tutoresAcademicos);
            this.cbFechasDeSesionDeTutoriaAcademica.setItems(sesionesDeTutoriaAcademica);
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
        TutorAcademico tutorAcademicoSeleccionado = this.cbTutoresAcademicos.getSelectionModel().getSelectedItem();
        SesionDeTutoriaAcademica sesionDeTutoriaAcademicaSeleccionada = this.cbFechasDeSesionDeTutoriaAcademica.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource(".fxml"));
            Parent raiz = cargadorFXML.load();
            ReporteDeTutoriaAcademicaControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.setTutorAcademico(tutorAcademicoSeleccionado);
            controladorGUI.setSesionDeTutoriaAcademica(sesionDeTutoriaAcademicaSeleccionada);
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Reporte de tutoría académica");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException ioException) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }

}
