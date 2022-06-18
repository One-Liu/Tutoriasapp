package uv.fei.tutorias.main;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import uv.fei.tutorias.domain.FechaDeCierreEntregaDeReporte;
import uv.fei.tutorias.domain.PeriodoEscolar;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

public class ModificacionDeFechaDeEntregaDeReporteControlador implements Initializable {

    @FXML
    private Label lblFechaSesionTutoria;
    @FXML
    private DatePicker dpFechaEntregaReporte;

    private final FechaDeCierreEntregaDeReporteDAO fechaDeCierreEntregaDeReporteDAO = new FechaDeCierreEntregaDeReporteDAO();

    private FechaDeCierreEntregaDeReporte fechaDeCierreEntregaDeReporte = new FechaDeCierreEntregaDeReporte();

    @Setter
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica();
    @Setter
    private PeriodoEscolar periodoEscolar = new PeriodoEscolar();

    public void cargarDatos() throws SQLException {
        this.fechaDeCierreEntregaDeReporte = fechaDeCierreEntregaDeReporteDAO.obtenerFechaDeCierreEntregaDeReportePorId(sesionDeTutoriaAcademica.getIdFechaDeCierreEntregaDeReporte());
    }

    public void cargarCamposGUI() {
        if(fechaDeCierreEntregaDeReporte.getFecha() == null) {
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                "Fecha de cierre de entrega de reporte",
                "No hay ninguna fecha de cierre de entrega de reporte registrada",
                Alert.AlertType.ERROR);
            UtilidadVentana.cerrarVentana(new ActionEvent());
            
        } else {
            this.lblFechaSesionTutoria.setText(sesionDeTutoriaAcademica.getFechaConFormato());
            this.dpFechaEntregaReporte.setValue(LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(fechaDeCierreEntregaDeReporte.getFecha())));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    private boolean validarFechaDeCierreIngresada() {
        boolean fechaDeCierreValida = true;

        LocalDate fechaTerminoPeriodoEscolar = (new java.sql.Date(this.periodoEscolar.getFechaTermino().getTime())).toLocalDate();
        LocalDate fechaDeSesionDeTutoriaAcademica = (new java.sql.Date(this.sesionDeTutoriaAcademica.getFecha().getTime())).toLocalDate();
        LocalDate nuevaFechaDeCierre = this.dpFechaEntregaReporte.getValue();

        if(!nuevaFechaDeCierre.isAfter(fechaDeSesionDeTutoriaAcademica)) {
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                "Fecha de cierre inválida",
                "La fecha de cierre para la entrega del reporte debe ser posterior a la fecha de sesión de tutoría académica (" 
                    + sesionDeTutoriaAcademica.getFechaConFormato() + ")",
                Alert.AlertType.WARNING);
            fechaDeCierreValida = false;
            
        } else if(!nuevaFechaDeCierre.isBefore(fechaTerminoPeriodoEscolar)) {
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                "Fecha de cierre inválida",
                "La fecha de cierre para la entrega del reporte debe ser antes de que termine el periodo escolar: \n" 
                    + periodoEscolar.getFechas(),
                Alert.AlertType.WARNING);
            fechaDeCierreValida = false;
        }

        return fechaDeCierreValida;
    }

    @FXML
    private void clicGuardar(ActionEvent evento) {
        if(validarFechaDeCierreIngresada()) {
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
    }

    @FXML
    private void clicCancelar(ActionEvent event) {
        UtilidadVentana.cerrarVentana(event);
    }
}
