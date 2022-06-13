package uv.fei.tutorias.main;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Setter;
import uv.fei.tutorias.bussinesslogic.ExperienciaEducativaDAO;
import uv.fei.tutorias.bussinesslogic.ProblematicaAcademicaDAO;
import uv.fei.tutorias.bussinesslogic.ProfesorDAO;
import uv.fei.tutorias.domain.Estudiante;
import uv.fei.tutorias.domain.ExperienciaEducativa;
import uv.fei.tutorias.domain.ProblematicaAcademica;
import uv.fei.tutorias.domain.Profesor;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

public class RegistroDeProblematicaAcademicaControlador implements Initializable {
    @FXML
    private ComboBox<Profesor> cbProfesores;
    @FXML
    private ComboBox<ExperienciaEducativa> cbExperienciasEducativas;
    @FXML
    private TextField tfProblematicaAcademica;
    @FXML
    private TextArea taDescripcion;
    @FXML
    private TableView<?> tblEstudiante_Presenta;
    @FXML
    private TableColumn<?,String> colEstudiante;
    @FXML
    private TableColumn<?,?> colPresenta;
    
    private ProfesorDAO profesorDAO = new ProfesorDAO();
    private ExperienciaEducativaDAO experienciaEducativaDAO = new ExperienciaEducativaDAO();
    private ProblematicaAcademicaDAO problematicaAcademicaDAO = new ProblematicaAcademicaDAO();
    
    private ObservableList<Profesor> profesores = FXCollections.observableArrayList();
    private ObservableList<ExperienciaEducativa> experienciasEducativas = FXCollections.observableArrayList();
    
    private ProblematicaAcademica problematicaAcademica = new ProblematicaAcademica();
    
    @Setter
    private ObservableList<Estudiante> estudiantesDelTutorAcademico = FXCollections.observableArrayList();
    @Setter
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica();
    
    private void cargarProfesores() throws SQLException {
        this.profesores = profesorDAO.obtenerProfesores();
    }
    
    private void cargarExperienciasEducativas() throws SQLException {
        this.experienciasEducativas = experienciaEducativaDAO.obtenerExperienciasEducativas();
    }
    
    private void inicializarTabla() {
        
    }
    
    private void cargarCamposGUI() {
        try {
            cargarProfesores();
            cargarExperienciasEducativas();
            this.cbProfesores.setItems(profesores);
            this.cbExperienciasEducativas.setItems(experienciasEducativas);
        } catch(SQLException ex) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCamposGUI();
    }
    
    private void registrarProblematicaAcademica() throws SQLException {
        this.problematicaAcademica.setTitulo(this.tfProblematicaAcademica.getText());
        this.problematicaAcademica.setDescripcion(this.taDescripcion.getText());
        this.problematicaAcademica.setIdSesionDeTutoriaAcademica(sesionDeTutoriaAcademica.getId());
        
        ExperienciaEducativa experienciaEducativaSeleccionada = this.cbExperienciasEducativas.getSelectionModel().getSelectedItem();
        Profesor profesorSeleccionado = this.cbProfesores.getSelectionModel().getSelectedItem();
        
        this.problematicaAcademica.setIdExperienciaEducativa(experienciaEducativaSeleccionada.getIdExperienciaEducativa());
        this.problematicaAcademica.setIdProfesor(profesorSeleccionado.getIdProfesor());
        
        problematicaAcademicaDAO.agregarProblematicaAcademica(problematicaAcademica);
    }
    
    private void registrarEstudiantesConProblematicasAcademicas() {
        
    }
    
    @FXML
    private void clicRegistrar(ActionEvent event) {
        try {
            registrarProblematicaAcademica();
        } catch(SQLException ex) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }
    
    @FXML
    private void clicCancelar(ActionEvent event) {
        UtilidadVentana.cerrarVentana(event);
    }
}
