package uv.fei.tutorias.main;

import java.net.PortUnreachableException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import uv.fei.tutorias.bussinesslogic.ExperienciaEducativaDAO;
import uv.fei.tutorias.bussinesslogic.ProblematicaAcademicaDAO;
import uv.fei.tutorias.bussinesslogic.ProfesorDAO;
import uv.fei.tutorias.domain.Estudiante;
import uv.fei.tutorias.domain.ExperienciaEducativa;
import uv.fei.tutorias.domain.ProblematicaAcademica;
import uv.fei.tutorias.domain.Profesor;

public class RegistroDeProblematicaAcademicaControlador implements Initializable {
    @FXML
    private ComboBox cbProfesores;
    @FXML
    private ComboBox cbExperienciasEducativas;
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
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnCancelar;
    
    private ProfesorDAO profesorDAO = new ProfesorDAO();
    private ExperienciaEducativaDAO experienciaEducativaDAO = new ExperienciaEducativaDAO();
    private ProblematicaAcademicaDAO problematicaAcademicaDAO = new ProblematicaAcademicaDAO();
    
    private ObservableList<Profesor> profesores = FXCollections.observableArrayList();
    private ObservableList<ExperienciaEducativa> experienciasEducativas = FXCollections.observableArrayList();
    private ObservableList<Estudiante> estudiantesDelTutorAcademico = FXCollections.observableArrayList();
    
    private ProblematicaAcademica problematicaAcademica = new ProblematicaAcademica();
    
    
    public void setEstudiantesDelTutor(ObservableList<Estudiante> estudiantesDelTutorAcademico) {
        this.estudiantesDelTutorAcademico = estudiantesDelTutorAcademico;
    }
    
    private void cargarProfesores() throws SQLException {
        this.profesores = profesorDAO.obtenerProfesores();
    }
    
    private void cargarExperienciasEducativas() throws SQLException {
        this.experienciasEducativas = experienciaEducativaDAO.obtenerExperienciasEducativas();
    }
    
    private void cerrarGUI() {
        Stage escenarioPrincipal = (Stage) this.btnCancelar.getScene().getWindow();
        escenarioPrincipal.close();
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
            Utilidad.mensajePerdidaDeConexion();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCamposGUI();
    }
    
    private void registrarProblematicaAcademica() throws SQLException {
        this.problematicaAcademica.setTitulo(this.tfProblematicaAcademica.getText());
        this.problematicaAcademica.setDescripcion(this.taDescripcion.getText());
        problematicaAcademicaDAO.agregarProblematicaAcademica(problematicaAcademica);
    }
    
    
    @FXML
    private void clicRegistrar(ActionEvent event) {
        try {
            registrarProblematicaAcademica();
        } catch(SQLException ex) {
            Utilidad.mensajePerdidaDeConexion();
        }
    }
    
    @FXML
    private void clicCancelar(ActionEvent event) {
        cerrarGUI();
    }
}
