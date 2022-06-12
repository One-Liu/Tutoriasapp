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
import uv.fei.tutorias.bussinesslogic.EstudianteDAO;
import uv.fei.tutorias.bussinesslogic.TutorAcademicoDAO;
import uv.fei.tutorias.domain.Estudiante;
import uv.fei.tutorias.domain.TutorAcademico;

public class ModificacionDeAsignacionDeTutorAcademicoControlador implements Initializable {
    @FXML
    private Label lblEstudianteSeleccionado;
    @FXML
    private Label lblTutorAcademicoAnterior;
    @FXML
    private ComboBox<TutorAcademico> cbTutoresAcademicos;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    
    private EstudianteDAO estudianteDAO = new EstudianteDAO();
    private TutorAcademicoDAO tutorAcademicoDAO = new TutorAcademicoDAO();
    
    private ObservableList<TutorAcademico> tutoresAcademicos = FXCollections.observableArrayList();
    
    private Estudiante estudiante = new Estudiante();
    private TutorAcademico tutorAcademicoAnterior = new TutorAcademico();
    
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    
    private void cargarTutoresAcademicos() throws SQLException {
        this.tutorAcademicoAnterior = tutorAcademicoDAO.obtenerTutorAcademicoPorId(this.estudiante.getIdTutorAcademico());
        this.tutoresAcademicos = tutorAcademicoDAO.obtenerTutoresAcademicosDistintosA(this.estudiante.getIdTutorAcademico());
    }
    
    private void cargarCamposGUI() {
        try {
            cargarTutoresAcademicos();
            this.lblEstudianteSeleccionado.setText(this.estudiante.getNombreCompleto());
            this.lblTutorAcademicoAnterior.setText(this.tutorAcademicoAnterior.getNombreCompleto());
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
    private void clicGuardar(ActionEvent event) {
        TutorAcademico tutorAcademicoSeleccionado = this.cbTutoresAcademicos.getSelectionModel().getSelectedItem();
        this.estudiante.setIdTutorAcademico(tutorAcademicoSeleccionado.getIdTutorAcademico());
        
        try {
            estudianteDAO.modificarAsignacionDeTutor(this.estudiante);
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                    "Confirmación de modificación", 
                    "La asignación de tutor se ha modificado exitosamente", 
                    Alert.AlertType.INFORMATION);
        } catch(SQLException ex) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
        
        UtilidadVentana.cerrarVentana(event);
    }
}
