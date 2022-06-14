package uv.fei.tutorias.main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Setter;
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
    
    private SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDAO = new SesionDeTutoriaAcademicaDAO();
    private PeriodoEscolarDAO periodoEscolarDAO = new PeriodoEscolarDAO();
    
    private ObservableList<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademica = FXCollections.observableArrayList();
    
    private SesionDeTutoriaAcademica primeraSesionDeTutoriaAcademica;
    private SesionDeTutoriaAcademica segundaSesionDeTutoriaAcademica;
    private SesionDeTutoriaAcademica terceraSesionDeTutoriaAcademica;
    
    @Setter
    private PeriodoEscolar periodoEscolar = new PeriodoEscolar();
    
    private void inicializarFechasDeSesionDeTutoriaAcademica() {
        this.primeraSesionDeTutoriaAcademica = sesionesDeTutoriaAcademica.get(0);
        this.segundaSesionDeTutoriaAcademica = sesionesDeTutoriaAcademica.get(1);
        this.terceraSesionDeTutoriaAcademica = sesionesDeTutoriaAcademica.get(2);
    }
    
    private void cargarSesionesDeTutoriaAcademica() throws SQLException {
        this.sesionesDeTutoriaAcademica = (ObservableList<SesionDeTutoriaAcademica>) sesionDeTutoriaAcademicaDAO.obtenerSesionDeTutoriaAcademicaPorPeriodoEscolar(periodoEscolar.getId());
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
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCamposGUI();
    }
    
    @FXML
    private void clicModificarFechasDeEntregaDeReporte(ActionEvent evento) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("/src/main/resources/uv/fei/tutorias/main/GUIModificacionDeFechaDeEntregaDeReporte.fxml"));
            Parent raiz = cargadorFXML.load();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Modificación de fecha de entrega de reporte");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException ioException) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }
    
    @FXML
    private void clicGuardar(ActionEvent evento) {
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
            UtilidadVentana.mostrarAlertaSinConfirmacion("", "", Alert.AlertType.INFORMATION);
            UtilidadVentana.cerrarVentana(evento);
        } catch(SQLException ex) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
        UtilidadVentana.cerrarVentana(evento);
    }
    
    @FXML
    private void clicCancelar(ActionEvent evento) {
        UtilidadVentana.cerrarVentana(evento);
    }
}
