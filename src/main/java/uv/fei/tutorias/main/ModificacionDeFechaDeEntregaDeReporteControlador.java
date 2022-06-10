package uv.fei.tutorias.main;

import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import uv.fei.tutorias.bussinesslogic.FechaDeCierreEntregaDeReporteDAO;
import uv.fei.tutorias.domain.FechaDeCierreEntregaDeReporte;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

public class ModificacionDeFechaDeEntregaDeReporteControlador implements Initializable {
    @FXML
    private Label lblFechaSesionTutoria;
    @FXML
    private DatePicker dpFechaEntregaReporte;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    
    private FechaDeCierreEntregaDeReporteDAO fechaDeCierreEntregaDeReporteDAO = new FechaDeCierreEntregaDeReporteDAO();
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica();
    private FechaDeCierreEntregaDeReporte fechaDeCierreEntregaDeReporte = new FechaDeCierreEntregaDeReporte();
    
    private void cerrarGUI() {
        Stage escenarioPrincipal = (Stage) this.btnCancelar.getScene().getWindow();
        escenarioPrincipal.close();
    }
    
    private void cargarFechaEntregaReporte() throws SQLException {
        this.fechaDeCierreEntregaDeReporte = fechaDeCierreEntregaDeReporteDAO.obtenerFechaDeCierreEntregaDeReportePorId(sesionDeTutoriaAcademica.getIdFechaDeCierreEntregaDeReporte());
    }
    
    private void cargarCamposGUI() {
        try {
            cargarFechaEntregaReporte();
            this.lblFechaSesionTutoria.setText(sesionDeTutoriaAcademica.getFechaConFormato());
            this.dpFechaEntregaReporte.setValue(fechaDeCierreEntregaDeReporte.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        } catch(SQLException ex) {
            Utilidad.mensajePerdidaDeConexion();
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
        
        try {
            // Puedo generar una nueva fecha de cierre y asignarsela solo a esa sesión
            // o modificar directamente la fecha (lo cual afectaría al resto de sesiones)
        } catch(SQLException ex) {
            Utilidad.mensajePerdidaDeConexion();
        }
    }
    
    @FXML
    private void clicCancelar(ActionEvent event) {
        cerrarGUI();
    }
}
