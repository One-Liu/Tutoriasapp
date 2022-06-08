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
import javafx.stage.Stage;
import uv.fei.tutorias.bussinesslogic.EstudianteDAO;
import uv.fei.tutorias.bussinesslogic.TutorAcademicoDAO;
import uv.fei.tutorias.domain.Estudiante;
import uv.fei.tutorias.domain.TutorAcademico;

public class AsignacionDeTutorAcademicoControlador implements Initializable {
    @FXML
    private ComboBox<Estudiante> cbEstudiantes;
    @FXML
    private ComboBox<TutorAcademico> cbTutoresAcademicos;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnCancelar;
    
    private ObservableList<Estudiante> estudiantes = FXCollections.observableArrayList();
    private ObservableList<TutorAcademico> tutoresAcademicos = FXCollections.observableArrayList();
    private EstudianteDAO estudianteDAO = new EstudianteDAO();
    private TutorAcademicoDAO tutorAcademicoDAO = new TutorAcademicoDAO();
    
    private void cerrarGUI() {
        Stage escenarioPrincipal = (Stage) this.btnCancelar.getScene().getWindow();
        escenarioPrincipal.close();
    }
    
    private void cargarEstudiantes() throws SQLException {
        this.estudiantes = estudianteDAO.obtenerEstudiantesSinTutorAsignado();
    }
    
    private void cargarTutoresAcademicos() throws SQLException {
        this.tutoresAcademicos = tutorAcademicoDAO.obtenerTutoresAcademicos();
    }
    
    private void cargarCamposGUI() {
        try {
            cargarEstudiantes();
            cargarTutoresAcademicos();
            this.cbEstudiantes.setItems(estudiantes);
            this.cbTutoresAcademicos.setItems(tutoresAcademicos);
        } catch(SQLException ex) {
            Utilidad.mensajePerdidaDeConexion();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCamposGUI();
    }
    
    @FXML
    private void clicCancelar(ActionEvent event) {
        cerrarGUI();
    }
    
    @FXML
    private void clicRegistrar(ActionEvent event) {
        Estudiante estudianteSeleccionado = this.cbEstudiantes.getSelectionModel().getSelectedItem();
        TutorAcademico tutorAcademicoSeleccionado = this.cbTutoresAcademicos.getSelectionModel().getSelectedItem();
        if(estudianteSeleccionado == null) {
            Utilidad.mostrarAlertaSinConfirmacion("Seleccion de estudiante", "Seleccione un estudiante válido", Alert.AlertType.WARNING);
        } else if(tutorAcademicoSeleccionado == null) {
            Utilidad.mostrarAlertaSinConfirmacion("Seleccion de tutor académico", "Seleccione un tutor académico válido", Alert.AlertType.WARNING);
        } else {
            estudianteSeleccionado.setIdTutorAcademico(tutorAcademicoSeleccionado.getId());
            try {
                estudianteDAO.modificarAsignacionDeTutor(estudianteSeleccionado);
                Utilidad.mostrarAlertaSinConfirmacion("", "", Alert.AlertType.INFORMATION);
            } catch(SQLException ex) {
                Utilidad.mensajePerdidaDeConexion();
            }
        }
        cerrarGUI();
    }
}
