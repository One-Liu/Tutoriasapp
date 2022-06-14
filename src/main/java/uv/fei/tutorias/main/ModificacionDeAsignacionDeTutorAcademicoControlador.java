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
import javafx.scene.control.Label;
import javafx.util.StringConverter;
import lombok.Setter;
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
    
    private final EstudianteDAO estudianteDAO = new EstudianteDAO();
    private final TutorAcademicoDAO tutorAcademicoDAO = new TutorAcademicoDAO();
    
    private ObservableList<TutorAcademico> tutoresAcademicos = FXCollections.observableArrayList();
    
    private TutorAcademico tutorAcademicoAnterior = new TutorAcademico();
    
    @Setter
    private Estudiante estudiante = new Estudiante();
    
    public void cargarDatos() {
        try {
            this.tutorAcademicoAnterior = tutorAcademicoDAO.obtenerTutorAcademicoPorId(this.estudiante.getIdTutorAcademico());
            this.tutoresAcademicos.addAll(tutorAcademicoDAO.obtenerTutoresAcademicosDistintosA(this.estudiante.getIdTutorAcademico()));
        } catch(SQLException ex) {
            UtilidadVentana.mensajePerdidaDeConexion();
            UtilidadVentana.cerrarVentana(new ActionEvent());
        }
    }
    
    public void cargarCamposGUI() {
        this.lblEstudianteSeleccionado.setText("(" + this.estudiante.getMatricula() + ") " + this.estudiante.getNombreCompleto());
        this.lblTutorAcademicoAnterior.setText("(" + this.tutorAcademicoAnterior.getIdTutorAcademico() + ") " + this.tutorAcademicoAnterior.getNombreCompleto());
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
        cargarCamposGUI();
    }
    
    @FXML
    private void clicCancelar(ActionEvent evento) {
        UtilidadVentana.cerrarVentana(evento);
    }
    
    @FXML
    private void clicGuardar(ActionEvent evento) {
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
        
        UtilidadVentana.cerrarVentana(evento);
    }
}
