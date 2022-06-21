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
import javafx.scene.control.Alert;
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
import uv.fei.tutorias.utilidades.TablaEstudiante_asistioEnRiesgo;

public class ReporteDeTutoriaAcademicaControlador implements Initializable {
    @FXML
    private Label lblPeriodoEscolar;
    @FXML
    private Label lblFechaSesion;
    @FXML
    private TextArea taDescripcion;
    @FXML
    private TableView<TablaEstudiante_asistioEnRiesgo> tblEstudiante_Asistencia_EnRiesgo;
    @FXML
    private TableColumn<TablaEstudiante_asistioEnRiesgo,String> colEstudiante;
    @FXML
    private TableColumn<TablaEstudiante_asistioEnRiesgo,CheckBox> colAsistencia;
    @FXML
    private TableColumn<TablaEstudiante_asistioEnRiesgo,CheckBox> colEnRiesgo;
    
    private ObservableList<TablaEstudiante_asistioEnRiesgo> listaDeAsistenciasEstudiantes = FXCollections.observableArrayList();
    
    private ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = new ReporteDeTutoriaAcademica();
    private PeriodoEscolar periodoEscolar = new PeriodoEscolar();
    
    @Setter
    private TutorAcademico tutorAcademico = new TutorAcademico();
    @Setter
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica();
    
    public void cargarDatos() throws SQLException {
        ReporteDeTutoriaAcademicaDAO reporteDeTutoriaAcademicaDAO = new ReporteDeTutoriaAcademicaDAO();
        PeriodoEscolarDAO periodoEscolarDAO = new PeriodoEscolarDAO();
        ListaDeAsistenciaDAO listaDeAsistenciaDAO = new ListaDeAsistenciaDAO();
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        
        this.periodoEscolar = periodoEscolarDAO.obtenerPeriodoEscolarPorId(sesionDeTutoriaAcademica.getIdPeriodoEscolar());
        
        this.reporteDeTutoriaAcademica.setIdTutorAcademico(this.tutorAcademico.getIdTutorAcademico());
        this.reporteDeTutoriaAcademica.setIdSesionDeTutoriaAcademica(this.sesionDeTutoriaAcademica.getId());
        this.reporteDeTutoriaAcademica = reporteDeTutoriaAcademicaDAO.obtenerReporteDeTutoriaPorIdSesionTutoriaYIdTutor(reporteDeTutoriaAcademica);

        ObservableList<ListaDeAsistencia> asistencias = FXCollections.observableArrayList();
        asistencias.addAll(listaDeAsistenciaDAO.obtenerListaDeAsistenciasPorTutorYSesionDeTutoria(this.tutorAcademico.getIdTutorAcademico(), this.sesionDeTutoriaAcademica.getId()));
        
        TablaEstudiante_asistioEnRiesgo visualizacionEstudiantes;
        
        for(ListaDeAsistencia listaDeAsistencia : asistencias) {
            visualizacionEstudiantes = new TablaEstudiante_asistioEnRiesgo();
            Estudiante estudianteRecuperado = estudianteDAO.obtenerEstudiantePorId(listaDeAsistencia.getIdEstudiante());
            CheckBox asistencia = new CheckBox();
            asistencia.setSelected(listaDeAsistencia.getAsistio());
            CheckBox enRiesgo = new CheckBox();
            enRiesgo.setSelected(estudianteRecuperado.getEnRiesgo());
            
            visualizacionEstudiantes.setEstudiante(estudianteRecuperado);
            visualizacionEstudiantes.setAsistio(asistencia);
            visualizacionEstudiantes.setEnRiesgo(enRiesgo);
            this.listaDeAsistenciasEstudiantes.add(visualizacionEstudiantes);
        }
    }
    
    private boolean validarDatos() {
        boolean datosValidos = true;
        
        if(reporteDeTutoriaAcademica.equals(new ReporteDeTutoriaAcademica())) {
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                "Reporte de tutoría académica sin registrar", 
                "El tutor académico no ha entregado el reporte de tutoría académica.", 
                Alert.AlertType.ERROR);
            datosValidos = false;
        }
        
        return datosValidos;
    }
    
    public void cargarCamposGUI() {
        if(validarDatos()) {
            this.lblPeriodoEscolar.setText(this.periodoEscolar.getFechas());
            this.lblFechaSesion.setText(this.sesionDeTutoriaAcademica.getFechaConFormato());
            this.taDescripcion.setText(this.reporteDeTutoriaAcademica.getDescripcionGeneral());

            this.colEstudiante.setCellValueFactory(new PropertyValueFactory("nombre"));
            this.colAsistencia.setCellValueFactory(new PropertyValueFactory("asistencia"));
            this.colEnRiesgo.setCellValueFactory(new PropertyValueFactory("enRiesgo"));

            this.tblEstudiante_Asistencia_EnRiesgo.setItems(this.listaDeAsistenciasEstudiantes);
            this.tblEstudiante_Asistencia_EnRiesgo.getSelectionModel().clearSelection();
        } else {
            UtilidadVentana.cerrarVentana(new ActionEvent());
        }
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
