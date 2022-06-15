package uv.fei.tutorias.main;

import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import lombok.Setter;
import uv.fei.tutorias.bussinesslogic.FechaDeCierreEntregaDeReporteDAO;
import uv.fei.tutorias.bussinesslogic.ReporteDeTutoriaAcademicaDAO;
import uv.fei.tutorias.domain.FechaDeCierreEntregaDeReporte;
import uv.fei.tutorias.domain.ReporteDeTutoriaAcademica;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

public class ModificacionDeFechaDeEntregaDeReporteControlador implements Initializable {
    @FXML
    private Label lblFechaSesionTutoria;
    @FXML
    private DatePicker dpFechaEntregaReporte;
    
    private final ReporteDeTutoriaAcademicaDAO reporteDeTutoriaAcademicaDAO = new ReporteDeTutoriaAcademicaDAO();
    private final FechaDeCierreEntregaDeReporteDAO fechaDeCierreEntregaDeReporteDAO = new FechaDeCierreEntregaDeReporteDAO();
    
    private ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = new ReporteDeTutoriaAcademica();
    private FechaDeCierreEntregaDeReporte fechaDeCierreEntregaDeReporte = new FechaDeCierreEntregaDeReporte();
    
    @Setter
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica();
    
    public void cargarDatos() {
        try {
            this.fechaDeCierreEntregaDeReporte = fechaDeCierreEntregaDeReporteDAO.obtenerFechaDeCierreEntregaDeReportePorId(reporteDeTutoriaAcademica.getIdFechaCierreEntregaReporte());
        } catch(SQLException ex) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }
    
    public void cargarCamposGUI() {
        this.lblFechaSesionTutoria.setText(sesionDeTutoriaAcademica.getFechaConFormato());
        this.dpFechaEntregaReporte.setValue(fechaDeCierreEntregaDeReporte.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCamposGUI();
    }
    
    @FXML
    private void clicGuardar(ActionEvent evento) {
        Date fechaSeleccionada = (Date) java.sql.Date.valueOf(dpFechaEntregaReporte.getValue());
        
        fechaDeCierreEntregaDeReporte.setFecha(fechaSeleccionada);
        
        try {
            fechaDeCierreEntregaDeReporteDAO.modificarFechaDeCierreEntregaDeReporte(fechaDeCierreEntregaDeReporte);
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                    "Confirmación de modificación", 
                    "La fecha de cierre para la entrega del reporte se ha modificado exitosamente", 
                    Alert.AlertType.INFORMATION);
            UtilidadVentana.cerrarVentana(evento);
        } catch(SQLException excepcionSQL) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }
    
    @FXML
    private void clicCancelar(ActionEvent event) {
        UtilidadVentana.cerrarVentana(event);
    }
}
