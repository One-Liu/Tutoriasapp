package uv.fei.tutorias.main;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import uv.fei.tutorias.bussinesslogic.ListaDeAsistenciaDAO;
import uv.fei.tutorias.bussinesslogic.PeriodoEscolarDAO;
import uv.fei.tutorias.domain.ListaDeAsistencia;
import uv.fei.tutorias.domain.PeriodoEscolar;
import uv.fei.tutorias.domain.ReporteDeTutoriaAcademica;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

public class ReporteDeTutoriaAcademicaControlador implements Initializable {
    @FXML
    private Label lblPeriodoEscolar;
    @FXML
    private Label lblFechaSesion;
    @FXML
    private TableView<?> tblEstudiante_Asistencia;
    @FXML
    private TableColumn<?,?> colEstudiante;
    @FXML
    private TableColumn<?,?> colAsistencia;
    @FXML
    private TextArea taDescripcion;
    @FXML
    private Button btnDescargar;
    @FXML
    private Button btnImprimir;
    @FXML
    private Button btnCancelar;
    
    private PeriodoEscolarDAO periodoEscolarDAO = new PeriodoEscolarDAO();
    private ListaDeAsistenciaDAO listaDeAsistenciaDAO = new ListaDeAsistenciaDAO();
    
    private ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = new ReporteDeTutoriaAcademica();
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica();
    private PeriodoEscolar periodoEscolar = new PeriodoEscolar();
    private ObservableList<ListaDeAsistencia> listaDeAsistencias = FXCollections.observableArrayList();
    
    private void cerrarGUI() {
        Stage escenarioPrincipal = (Stage) this.btnCancelar.getScene().getWindow();
        escenarioPrincipal.close();
    }
    
    public void setReporteDeTutoriaAcademica(ReporteDeTutoriaAcademica reporteDeTutoriaAcademica) {
        this.reporteDeTutoriaAcademica = reporteDeTutoriaAcademica;
    }
    
    public void setSesionDeTutoriaAcademica(SesionDeTutoriaAcademica sesionDeTutoriaAcademica) {
        this.sesionDeTutoriaAcademica = sesionDeTutoriaAcademica;
    }
    
    private void inicializarPeriodoEscolar() throws SQLException {
        this.periodoEscolar = periodoEscolarDAO.obtenerPeriodoEscolarPorId(sesionDeTutoriaAcademica.getIdPeriodoEscolar());
    }
    
    private void inicializarListaDeAsistencias() throws SQLException {
        // Lista de asistencia pero de solo los estudiantes del tutor ??
        //this.listaDeAsistencias = listaDeAsistenciaDAO.buscarListasDeAsistenciasPorIdSesiondeTutoriaAcademica(sesionDeTutoriaAcademica.getId());
    }
    
    private void cargarCamposGUI() {
        try {
            inicializarPeriodoEscolar();
            inicializarListaDeAsistencias();
        } catch(SQLException ex) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCamposGUI();
    }
    
    @FXML
    private void clicDescargar(ActionEvent event) {
        
    }
    
    @FXML
    private void clicImprimir(ActionEvent event) {
        
    }
    
    @FXML
    private void clicCancelar(ActionEvent event) {
        cerrarGUI();
    }
}
