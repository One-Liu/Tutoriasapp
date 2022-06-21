package uv.fei.tutorias.main;

import uv.fei.tutorias.utilidades.UtilidadVentana;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;
import uv.fei.tutorias.bussinesslogic.EstudianteDAO;
import uv.fei.tutorias.bussinesslogic.ListaDeAsistenciaDAO;
import uv.fei.tutorias.bussinesslogic.PeriodoEscolarDAO;
import uv.fei.tutorias.bussinesslogic.ReporteDeTutoriaAcademicaDAO;
import uv.fei.tutorias.domain.Estudiante;
import uv.fei.tutorias.domain.ListaDeAsistencia;
import uv.fei.tutorias.domain.PeriodoEscolar;
import uv.fei.tutorias.domain.ReporteDeTutoriaAcademica;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;
import uv.fei.tutorias.domain.TutorAcademico;
import uv.fei.tutorias.utilidades.TablaEstudiante_Asistencia_EnRiesgo;

public class ReporteDeTutoriaAcademicaControlador implements Initializable {
    @FXML
    private Label lblPeriodoEscolar;
    @FXML
    private Label lblFechaSesion;
    @FXML
    private TextArea taDescripcion;
    @FXML
    private TableView<TablaEstudiante_Asistencia_EnRiesgo> tblEstudiante_Asistencia_EnRiesgo;
    @FXML
    private TableColumn<TablaEstudiante_Asistencia_EnRiesgo,String> colEstudiante;
    @FXML
    private TableColumn<TablaEstudiante_Asistencia_EnRiesgo,CheckBox> colAsistencia;
    @FXML
    private TableColumn<TablaEstudiante_Asistencia_EnRiesgo,CheckBox> colEnRiesgo;
    
    private ObservableList<TablaEstudiante_Asistencia_EnRiesgo> listaDeAsistencias = FXCollections.observableArrayList();
    private ObservableList<Estudiante> estudiantesDelTutorAcademico = FXCollections.observableArrayList();
    
    private ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = new ReporteDeTutoriaAcademica();
    private PeriodoEscolar periodoEscolar = new PeriodoEscolar();
    
    @Setter
    private TutorAcademico tutorAcademico = new TutorAcademico();
    @Setter
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica();
    
    private void cargarDatos() throws SQLException {
        ReporteDeTutoriaAcademicaDAO reporteDeTutoriaAcademicaDAO = new ReporteDeTutoriaAcademicaDAO();
        PeriodoEscolarDAO periodoEscolarDAO = new PeriodoEscolarDAO();
        ListaDeAsistenciaDAO listaDeAsistenciaDAO = new ListaDeAsistenciaDAO();
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        
        this.periodoEscolar = periodoEscolarDAO.obtenerPeriodoEscolarPorId(sesionDeTutoriaAcademica.getIdPeriodoEscolar());
        
        this.reporteDeTutoriaAcademica.setIdTutorAcademico(this.tutorAcademico.getIdTutorAcademico());
        this.reporteDeTutoriaAcademica.setIdSesionDeTutoriaAcademica(this.sesionDeTutoriaAcademica.getId());
        this.reporteDeTutoriaAcademica = reporteDeTutoriaAcademicaDAO.obtenerReporteDeTutoriaPorIdSesionTutoriaYIdTutor(reporteDeTutoriaAcademica);
        
        // Obtener solo los del tutor académico
        this.listaDeAsistencias.addAll(listaDeAsistenciaDAO.buscarListasDeAsistenciasPorIdSesiondeTutoriaAcademica(sesionDeTutoriaAcademica.getId()));
        
        for(ListaDeAsistencia listaDeAsistencia : listaDeAsistencias) {
            this.estudiantesDelTutorAcademico.add(estudianteDAO.obtenerEstudiantePorId(listaDeAsistencia.getIdEstudiante()));
        }
    }
    
    private void cargarCamposGUI() {
        this.lblPeriodoEscolar.setText(this.periodoEscolar.getFechas());
        this.lblFechaSesion.setText(this.sesionDeTutoriaAcademica.getFechaConFormato());
        this.taDescripcion.setText(this.reporteDeTutoriaAcademica.getDescripcionGeneral());
        
        this.colEstudiante.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colAsistencia.setCellValueFactory(new PropertyValueFactory("asistencia"));
        this.colEnRiesgo.setCellValueFactory(new PropertyValueFactory("enRiesgo"));
        
        this.tblEstudiante_Asistencia_EnRiesgo.setItems(estudiantesDelTutorAcademico);
        this.tblEstudiante_Asistencia_EnRiesgo.getSelectionModel().clearSelection();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
