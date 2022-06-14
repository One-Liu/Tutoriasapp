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
import javafx.scene.control.ComboBox;
import uv.fei.tutorias.bussinesslogic.EstudianteDAO;
import uv.fei.tutorias.bussinesslogic.TutorAcademicoDAO;
import uv.fei.tutorias.domain.Estudiante;
import uv.fei.tutorias.domain.TutorAcademico;

public class AsignacionDeTutorAcademicoControlador implements Initializable {
    @FXML
    private ComboBox<Estudiante> cbEstudiantes;
    @FXML
    private ComboBox<TutorAcademico> cbTutoresAcademicos;
    
    private EstudianteDAO estudianteDAO = new EstudianteDAO();
    private TutorAcademicoDAO tutorAcademicoDAO = new TutorAcademicoDAO();
    
    private ObservableList<Estudiante> estudiantes = FXCollections.observableArrayList();
    private ObservableList<TutorAcademico> tutoresAcademicos = FXCollections.observableArrayList();
    
    private void cargarEstudiantes() throws SQLException {
        this.estudiantes.addAll(estudianteDAO.obtenerEstudiantesSinTutorAsignado());
    }
    
    private void cargarTutoresAcademicos() throws SQLException {
        this.tutoresAcademicos.addAll(tutorAcademicoDAO.obtenerTutoresAcademicos())  ;
    }
    
    private void cargarCamposGUI() {
        try {
            cargarEstudiantes();
            cargarTutoresAcademicos();
            this.cbEstudiantes.setItems(estudiantes);
            this.cbTutoresAcademicos.setItems(tutoresAcademicos);
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
    private void clicRegistrar(ActionEvent event) {
        Estudiante estudianteSeleccionado = this.cbEstudiantes.getSelectionModel().getSelectedItem();
        TutorAcademico tutorAcademicoSeleccionado = this.cbTutoresAcademicos.getSelectionModel().getSelectedItem();
        estudianteSeleccionado.setIdTutorAcademico(tutorAcademicoSeleccionado.getIdTutorAcademico());
        try {
            estudianteDAO.modificarAsignacionDeTutor(estudianteSeleccionado);
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                    "Confirmación de registro", 
                    "La asignación de tutor académico ha sido registrada correctamente", 
                    Alert.AlertType.INFORMATION);
        } catch(SQLException ex) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }
}
