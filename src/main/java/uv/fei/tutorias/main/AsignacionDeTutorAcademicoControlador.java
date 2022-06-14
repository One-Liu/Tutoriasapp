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
import javafx.util.StringConverter;
import uv.fei.tutorias.bussinesslogic.EstudianteDAO;
import uv.fei.tutorias.bussinesslogic.TutorAcademicoDAO;
import uv.fei.tutorias.domain.Estudiante;
import uv.fei.tutorias.domain.TutorAcademico;

public class AsignacionDeTutorAcademicoControlador implements Initializable {
    @FXML
    private ComboBox<Estudiante> cbEstudiantes;
    @FXML
    private ComboBox<TutorAcademico> cbTutoresAcademicos;
    
    private final EstudianteDAO estudianteDAO = new EstudianteDAO();
    private final TutorAcademicoDAO tutorAcademicoDAO = new TutorAcademicoDAO();
    
    private ObservableList<Estudiante> estudiantes = FXCollections.observableArrayList();
    private ObservableList<TutorAcademico> tutoresAcademicos = FXCollections.observableArrayList();
    
    private void cargarDatos() {
        try {
            this.estudiantes.addAll(estudianteDAO.obtenerEstudiantesSinTutorAsignado());
            this.tutoresAcademicos.addAll(tutorAcademicoDAO.obtenerTutoresAcademicos());
        } catch(SQLException ex) {
            UtilidadVentana.mensajePerdidaDeConexion();
            UtilidadVentana.cerrarVentana(new ActionEvent());
        }
    }
    
    private void cargarCamposGUI() {
        this.cbEstudiantes.setItems(estudiantes);
        this.cbEstudiantes.getSelectionModel().selectFirst();
        this.cbEstudiantes.setConverter(new StringConverter<Estudiante>() {
            @Override
            public String toString(Estudiante estudiante) {
                return estudiante == null ? null : "(" + estudiante.getMatricula() + ") " + estudiante.getNombreCompleto();
            }

            @Override
            public Estudiante fromString(String string) {
                throw new UnsupportedOperationException("Operación no soportada");
            }
        });
        
        this.cbTutoresAcademicos.setItems(tutoresAcademicos);
        this.cbTutoresAcademicos.getSelectionModel().selectFirst();
        this.cbTutoresAcademicos.setConverter(new StringConverter<TutorAcademico>() {
            @Override
            public String toString(TutorAcademico tutorAcademico) {
                return tutorAcademico == null ? null : "(" + tutorAcademico.getIdTutorAcademico() + ") " + tutorAcademico.getNombreCompleto();
            }

            @Override
            public TutorAcademico fromString(String string) {
                throw new UnsupportedOperationException("Operación no soportada");
            }
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        cargarCamposGUI();
    }
    
    @FXML
    private void clicCancelar(ActionEvent evento) {
        UtilidadVentana.cerrarVentana(evento);
    }
    
    @FXML
    private void clicRegistrar(ActionEvent evento) {
        Estudiante estudianteSeleccionado = this.cbEstudiantes.getSelectionModel().getSelectedItem();
        TutorAcademico tutorAcademicoSeleccionado = this.cbTutoresAcademicos.getSelectionModel().getSelectedItem();
        estudianteSeleccionado.setIdTutorAcademico(tutorAcademicoSeleccionado.getIdTutorAcademico());
        try {
            estudianteDAO.modificarAsignacionDeTutor(estudianteSeleccionado);
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                    "Confirmación de registro", 
                    "La asignación de tutor académico ha sido registrada correctamente", 
                    Alert.AlertType.INFORMATION);
            
            this.cbEstudiantes.getItems().clear();
            this.cbTutoresAcademicos.getItems().clear();
            cargarDatos();
            
            if(this.estudiantes.isEmpty()) {
                UtilidadVentana.mostrarAlertaSinConfirmacion("Asignaciones completas", "Se les ha asignado un tutor a todos los estudiantes", Alert.AlertType.INFORMATION);
                UtilidadVentana.cerrarVentana(evento);
            } else {
                cargarCamposGUI();
            }
        } catch(SQLException ex) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }
}
