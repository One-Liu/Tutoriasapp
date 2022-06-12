package uv.fei.tutorias.main;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import uv.fei.tutorias.bussinesslogic.ListaDeAsistenciaDAO;
import uv.fei.tutorias.bussinesslogic.PeriodoEscolarDAO;
import uv.fei.tutorias.bussinesslogic.ReporteDeTutoriaAcademicaDAO;
import uv.fei.tutorias.domain.ListaDeAsistencia;
import uv.fei.tutorias.domain.PeriodoEscolar;
import uv.fei.tutorias.domain.ReporteDeTutoriaAcademica;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;
import uv.fei.tutorias.domain.TutorAcademico;

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
    
    private final ReporteDeTutoriaAcademicaDAO reporteDeTutoriaAcademicaDAO = new ReporteDeTutoriaAcademicaDAO();
    private final PeriodoEscolarDAO periodoEscolarDAO = new PeriodoEscolarDAO();
    private final ListaDeAsistenciaDAO listaDeAsistenciaDAO = new ListaDeAsistenciaDAO();
    
    private ObservableList<ListaDeAsistencia> listaDeAsistencias = FXCollections.observableArrayList();
    
    private TutorAcademico tutorAcademico = new TutorAcademico();
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica();
    private ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = new ReporteDeTutoriaAcademica();
    private PeriodoEscolar periodoEscolar = new PeriodoEscolar();
    
    public void setTutorAcademico(TutorAcademico tutorAcademico) {
        this.tutorAcademico = tutorAcademico;
    }
    
    public void setSesionDeTutoriaAcademica(SesionDeTutoriaAcademica sesionDeTutoriaAcademica) {
        this.sesionDeTutoriaAcademica = sesionDeTutoriaAcademica;
    }
    
    private void inicializarPeriodoEscolar() throws SQLException {
        this.periodoEscolar = periodoEscolarDAO.obtenerPeriodoEscolarPorId(sesionDeTutoriaAcademica.getIdPeriodoEscolar());
    }
    
    private void inicializarReporteDeTutoriaAcademica() throws SQLException {
        this.reporteDeTutoriaAcademica.setIdTutorAcademico(this.tutorAcademico.getIdTutorAcademico());
        this.reporteDeTutoriaAcademica.setIdSesionDeTutoriaAcademica(this.sesionDeTutoriaAcademica.getId());
        this.reporteDeTutoriaAcademica = reporteDeTutoriaAcademicaDAO.obtenerReporteDeTutoriaPorIdSesionTutoriaYIdTutor(reporteDeTutoriaAcademica);
    }
    
    private void inicializarListaDeAsistencias() throws SQLException {
        // Obtener solo los del tutor académico
        this.listaDeAsistencias = listaDeAsistenciaDAO.buscarListasDeAsistenciasPorIdSesiondeTutoriaAcademica(sesionDeTutoriaAcademica.getId());
    }
    
    private void cargarCamposGUI() {
        try {
            inicializarPeriodoEscolar();
            inicializarReporteDeTutoriaAcademica();
            inicializarListaDeAsistencias();
            this.lblPeriodoEscolar.setText(this.periodoEscolar.getFechas());
            this.lblFechaSesion.setText(this.sesionDeTutoriaAcademica.getFechaConFormato());
            //Inicializar lista de asistencia
            this.taDescripcion.setText(this.reporteDeTutoriaAcademica.getDescripcionGeneral());
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
        UtilidadVentana.cerrarVentana(event);
    }
}
