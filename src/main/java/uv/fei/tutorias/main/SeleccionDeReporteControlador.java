package uv.fei.tutorias.main;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import uv.fei.tutorias.bussinesslogic.SesionDeTutoriaAcademicaDAO;
import uv.fei.tutorias.bussinesslogic.TutorAcademicoDAO;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;
import uv.fei.tutorias.domain.TutorAcademico;

public class SeleccionDeReporteControlador implements Initializable {

    @FXML
    private ComboBox<TutorAcademico> cbTutoresAcademicos;
    @FXML
    private ComboBox<SesionDeTutoriaAcademica> cbFechasDeSesionDeTutoriaAcademica;
    @FXML
    private Label lblTutorAcademico;
    @FXML
    private Label lblFechaDeSesionDeTutoriaAcademica;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSeleccionar;
    
    private ObservableList<TutorAcademico> tutoresAcademicos = FXCollections.observableArrayList();
    private ObservableList<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademica = FXCollections.observableArrayList();
    
    private void cargarTutoresAcademicos() throws SQLException {
        TutorAcademicoDAO tutorAcademicoDAO = new TutorAcademicoDAO();
        this.tutoresAcademicos = tutorAcademicoDAO.obtenerTutoresAcademicos();
    }
    
    private void inicializarCBTutoresAcademicos() {
        this.cbTutoresAcademicos.setItems(tutoresAcademicos);
    }
    
    private void cargarSesionesDeTutoriaAcademica() throws SQLException {
        SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDAO = new SesionDeTutoriaAcademicaDAO();
        this.sesionesDeTutoriaAcademica = sesionDeTutoriaAcademicaDAO.obtenerSesionesDeTutoriaAcademica();
    }
    
    private void inicializarCBFechasDeSesionDeTutoriaAcademica() {
        this.cbFechasDeSesionDeTutoriaAcademica.setItems(sesionesDeTutoriaAcademica);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cargarTutoresAcademicos();
            cargarSesionesDeTutoriaAcademica();
            inicializarCBTutoresAcademicos();
            inicializarCBFechasDeSesionDeTutoriaAcademica();
        } catch(SQLException ex) {
            Utilidad.mensajePerdidaDeConexion();
        }
    }    
    
    @FXML
    private void clicCancelar(ActionEvent event) {
    }

    @FXML
    private void clicSeleccionar(ActionEvent event) {
        TutorAcademico tutorAcademicoSeleccionado = this.cbTutoresAcademicos.getSelectionModel().getSelectedItem();
        SesionDeTutoriaAcademica sesionDeTutoriaAcademicaSeleccionada = this.cbFechasDeSesionDeTutoriaAcademica.getSelectionModel().getSelectedItem();
        if(tutorAcademicoSeleccionado == null) {
            Utilidad.mostrarAlertaSinConfirmacion("Seleccion de tutor", "Seleccione un tutor académico válido", Alert.AlertType.WARNING);
        } else if(sesionDeTutoriaAcademicaSeleccionada == null) {
            Utilidad.mostrarAlertaSinConfirmacion("Seleccion de fecha de sesión", "Seleccione una fecha de sesión válida", Alert.AlertType.WARNING);
        } else {
            
        }
    }

}
