package uv.fei.tutorias.main;

import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import uv.fei.tutorias.bussinesslogic.PeriodoEscolarDAO;
import uv.fei.tutorias.bussinesslogic.SesionDeTutoriaAcademicaDAO;
import uv.fei.tutorias.domain.PeriodoEscolar;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

public class ModificacionDeFechasDeSesionDeTutoriaControlador implements Initializable {
    @FXML
    private Label lblPeriodoEscolar;
    @FXML
    private DatePicker dpPrimeraSesion;
    @FXML
    private DatePicker dpSegundaSesion;
    @FXML
    private DatePicker dpTerceraSesion;
    @FXML
    private Button btnModificarFechasDeEntregaDeReporte;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    
    private SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDAO = new SesionDeTutoriaAcademicaDAO();
    private PeriodoEscolarDAO periodoEscolarDAO = new PeriodoEscolarDAO();
    private PeriodoEscolar periodoEscolar = new PeriodoEscolar();
    private ObservableList<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademica = FXCollections.observableArrayList();
    private SesionDeTutoriaAcademica primeraSesionDeTutoriaAcademica;
    private SesionDeTutoriaAcademica segundaSesionDeTutoriaAcademica;
    private SesionDeTutoriaAcademica terceraSesionDeTutoriaAcademica;
    
    private void cerrarGUI() {
        Stage escenarioPrincipal = (Stage) this.btnCancelar.getScene().getWindow();
        escenarioPrincipal.close();
    }
    
    public void setPeriodoEscolar(PeriodoEscolar periodoEscolar) {
        this.periodoEscolar = periodoEscolar;
    }
    
    private void inicializarFechasDeSesionDeTutoriaAcademica() {
        this.primeraSesionDeTutoriaAcademica = sesionesDeTutoriaAcademica.get(0);
        this.segundaSesionDeTutoriaAcademica = sesionesDeTutoriaAcademica.get(1);
        this.terceraSesionDeTutoriaAcademica = sesionesDeTutoriaAcademica.get(2);
    }
    
    private void cargarSesionesDeTutoriaAcademica() throws SQLException {
        this.sesionesDeTutoriaAcademica = sesionDeTutoriaAcademicaDAO.obtenerSesionDeTutoriaAcademicaPorPeriodoEscolar(periodoEscolar.getId());
        inicializarFechasDeSesionDeTutoriaAcademica();
    }
    
    private void inicializarDatePickers() {
        this.dpPrimeraSesion.setValue(primeraSesionDeTutoriaAcademica.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        this.dpSegundaSesion.setValue(segundaSesionDeTutoriaAcademica.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        this.dpTerceraSesion.setValue(terceraSesionDeTutoriaAcademica.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
    
    private void cargarCamposGUI() {
        try {
            cargarSesionesDeTutoriaAcademica();
            this.lblPeriodoEscolar.setText(this.periodoEscolar.getFechas());
        } catch(SQLException ex) {
            Utilidad.mensajePerdidaDeConexion();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCamposGUI();
    }
    
    @FXML
    private void clicModificarFechasDeEntregaDeReporte() {
        
    }
    
    @FXML
    private void clicGuardar(ActionEvent event) {
        Date fechaPrimeraSesionSeleccionada = (Date) java.sql.Date.valueOf(dpPrimeraSesion.getValue());
        Date fechaSegundaSesionSeleccionada = (Date) java.sql.Date.valueOf(dpSegundaSesion.getValue());
        Date fechaTerceraSesionSeleccionada = (Date) java.sql.Date.valueOf(dpTerceraSesion.getValue());
        
        primeraSesionDeTutoriaAcademica.setFecha(fechaPrimeraSesionSeleccionada);
        segundaSesionDeTutoriaAcademica.setFecha(fechaSegundaSesionSeleccionada);
        terceraSesionDeTutoriaAcademica.setFecha(fechaTerceraSesionSeleccionada);
        
        try {
            sesionDeTutoriaAcademicaDAO.modificarFechaDeSesionDeTutoriaAcademica(primeraSesionDeTutoriaAcademica);
            sesionDeTutoriaAcademicaDAO.modificarFechaDeSesionDeTutoriaAcademica(segundaSesionDeTutoriaAcademica);
            sesionDeTutoriaAcademicaDAO.modificarFechaDeSesionDeTutoriaAcademica(terceraSesionDeTutoriaAcademica);
            Utilidad.mostrarAlertaSinConfirmacion("", "", Alert.AlertType.INFORMATION);
            cerrarGUI();
        } catch(SQLException ex) {
            Utilidad.mensajePerdidaDeConexion();
        }
    }
    
    @FXML
    private void clicCancelar(ActionEvent event) {
        cerrarGUI();
    }
}
