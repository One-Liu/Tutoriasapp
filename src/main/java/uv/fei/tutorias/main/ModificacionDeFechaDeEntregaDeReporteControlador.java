package uv.fei.tutorias.main;

import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import uv.fei.tutorias.bussinesslogic.FechaDeCierreEntregaDeReporteDAO;
import uv.fei.tutorias.bussinesslogic.ReporteDeTutoriaAcademicaDAO;
import uv.fei.tutorias.bussinesslogic.SesionDeTutoriaAcademicaDAO;
import uv.fei.tutorias.domain.FechaDeCierreEntregaDeReporte;
import uv.fei.tutorias.domain.ReporteDeTutoriaAcademica;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

public class ModificacionDeFechaDeEntregaDeReporteControlador implements Initializable {
    @FXML
    private Label lblFechaSesionTutoria;
    @FXML
    private DatePicker dpFechaEntregaReporte;
    
    private ReporteDeTutoriaAcademicaDAO reporteDeTutoriaAcademicaDAO = new ReporteDeTutoriaAcademicaDAO();
    private SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDAO = new SesionDeTutoriaAcademicaDAO();
    private FechaDeCierreEntregaDeReporteDAO fechaDeCierreEntregaDeReporteDAO = new FechaDeCierreEntregaDeReporteDAO();
    
    private ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = new ReporteDeTutoriaAcademica();
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica();
    private FechaDeCierreEntregaDeReporte fechaDeCierreEntregaDeReporte = new FechaDeCierreEntregaDeReporte();
    
    private void cargarFechaDeSesionDeTutoria() throws SQLException {
        this.sesionDeTutoriaAcademica = sesionDeTutoriaAcademicaDAO.obtenerSesionDeTutoriaAcademicaPorId(reporteDeTutoriaAcademica.getIdSesionDeTutoriaAcademica());
    }
    
    private void cargarFechaEntregaReporte() throws SQLException {
        this.fechaDeCierreEntregaDeReporte = fechaDeCierreEntregaDeReporteDAO.obtenerFechaDeCierreEntregaDeReportePorId(reporteDeTutoriaAcademica.getIdFechaCierreEntregaReporte());
    }
    
    private void cargarCamposGUI() {
        try {
            cargarFechaDeSesionDeTutoria();
            cargarFechaEntregaReporte();
            this.lblFechaSesionTutoria.setText(sesionDeTutoriaAcademica.getFechaConFormato());
            this.dpFechaEntregaReporte.setValue(fechaDeCierreEntregaDeReporte.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        } catch(SQLException ex) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCamposGUI();
    }
    
    @FXML
    private void clicGuardar(ActionEvent event) {
        Date fechaSeleccionada = (Date) java.sql.Date.valueOf(dpFechaEntregaReporte.getValue());
        
        fechaDeCierreEntregaDeReporte.setFecha(fechaSeleccionada);
        

    }
    
    @FXML
    private void clicCancelar(ActionEvent event) {
        UtilidadVentana.cerrarVentana(event);
    }
}
