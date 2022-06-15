package uv.fei.tutorias.main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Setter;
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

    private final SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDAO = new SesionDeTutoriaAcademicaDAO();

    private ObservableList<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademica = FXCollections.observableArrayList();

    private SesionDeTutoriaAcademica primeraSesionDeTutoriaAcademica;
    private SesionDeTutoriaAcademica segundaSesionDeTutoriaAcademica;
    private SesionDeTutoriaAcademica terceraSesionDeTutoriaAcademica;

    @Setter
    private PeriodoEscolar periodoEscolar = new PeriodoEscolar();

    public void cargarDatos() {
        try {
            this.sesionesDeTutoriaAcademica.addAll(sesionDeTutoriaAcademicaDAO.obtenerSesionDeTutoriaAcademicaPorPeriodoEscolar(periodoEscolar.getId()));
        } catch(SQLException ex) {
            UtilidadVentana.mensajePerdidaDeConexion();
        } finally {
            if(sesionesDeTutoriaAcademica.isEmpty()) {
                UtilidadVentana.mostrarAlertaSinConfirmacion(
                    "Periodo escolar sin fechas registradas",
                    "El periodo escolar seleccionado aún no tiene ninguna fecha de sesión de tutoría registrada",
                    Alert.AlertType.ERROR);
                UtilidadVentana.cerrarVentana(new ActionEvent());

            } else {
                cargarCamposGUI();
            }
        }
    }

    public void cargarCamposGUI() {
        this.primeraSesionDeTutoriaAcademica = sesionesDeTutoriaAcademica.get(0);
        this.segundaSesionDeTutoriaAcademica = sesionesDeTutoriaAcademica.get(1);
        this.terceraSesionDeTutoriaAcademica = sesionesDeTutoriaAcademica.get(2);

        this.lblPeriodoEscolar.setText(this.periodoEscolar.getFechas());
        this.dpPrimeraSesion.setValue(LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(primeraSesionDeTutoriaAcademica.getFecha())));
        this.dpSegundaSesion.setValue(LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(segundaSesionDeTutoriaAcademica.getFecha())));
        this.dpTerceraSesion.setValue(LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(terceraSesionDeTutoriaAcademica.getFecha())));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCamposGUI();
    }

    @FXML
    private void clicModificarFechasDeEntregaDeReporte(ActionEvent evento) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUISeleccionDeSesionDeTutoria.fxml"));
            Parent raiz = cargadorFXML.load();
            SeleccionDeSesionDeTutoriaControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.setSesionesDeTutoriaAcademica(sesionesDeTutoriaAcademica);
            controladorGUI.cargarCamposGUI();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Selección de sesión de tutoría");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException ioException) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }

    private boolean validarFechasIngresadas() {
        boolean fechasValidas = true;

        // Util.date a sql.Date(intermedio) a LocalDate
        LocalDate localDateFechaInicioPeriodoEscolar = (new java.sql.Date(this.periodoEscolar.getFechaInicio().getTime())).toLocalDate();
        LocalDate localDateFechaTerminoPeriodoEscolar = (new java.sql.Date(this.periodoEscolar.getFechaTermino().getTime())).toLocalDate();

        if(!this.dpPrimeraSesion.getValue().isAfter(localDateFechaInicioPeriodoEscolar)
            || !this.dpPrimeraSesion.getValue().isBefore(localDateFechaTerminoPeriodoEscolar)) {
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                "Fecha inválida",
                "La primera fecha de sesión de tutoría no está indicada dentro del periodo escolar",
                Alert.AlertType.WARNING);
            fechasValidas = false;

        } else if(!this.dpSegundaSesion.getValue().isAfter(localDateFechaInicioPeriodoEscolar)
            || !this.dpSegundaSesion.getValue().isBefore(localDateFechaTerminoPeriodoEscolar)) {
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                "Fecha inválida",
                "La segunda fecha de sesión de tutoría no está indicada dentro del periodo escolar",
                Alert.AlertType.WARNING);
            fechasValidas = false;

        } else if(!this.dpTerceraSesion.getValue().isAfter(localDateFechaInicioPeriodoEscolar)
            || !this.dpTerceraSesion.getValue().isBefore(localDateFechaTerminoPeriodoEscolar)) {
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                "Fecha inválida",
                "La tercera fecha de sesión de tutoría no está indicada dentro del periodo escolar",
                Alert.AlertType.WARNING);
            fechasValidas = false;

        } else if(!this.dpPrimeraSesion.getValue().isBefore(this.dpSegundaSesion.getValue())
            || !this.dpPrimeraSesion.getValue().isBefore(this.dpTerceraSesion.getValue())) {
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                "Fecha inválida",
                "La primera fecha de sesión de tutoría debe ocurrir antes de la segunda y tercer fecha de tutoría",
                Alert.AlertType.WARNING);
            fechasValidas = false;

        } else if(!this.dpSegundaSesion.getValue().isAfter(this.dpPrimeraSesion.getValue())
            || !this.dpSegundaSesion.getValue().isBefore(this.dpTerceraSesion.getValue())) {
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                "Fecha inválida",
                "La segunda fecha de sesión de tutoría debe ocurrir después de la primera y antes de la tercer fecha de tutoría",
                Alert.AlertType.WARNING);
            fechasValidas = false;

        } else if(!this.dpTerceraSesion.getValue().isAfter(this.dpPrimeraSesion.getValue())
            || !this.dpTerceraSesion.getValue().isAfter(this.dpSegundaSesion.getValue())) {
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                "Fecha inválida",
                "La tercer fecha de sesión de tutoría debe ocurrir después de la primera y segunda fecha de tutoría",
                Alert.AlertType.WARNING);
            fechasValidas = false;
        }

        return fechasValidas;
    }

    @FXML
    private void clicGuardar(ActionEvent evento) {
        if(validarFechasIngresadas()) {
            // LocalDate (del combobox) a sql.Date (intermedio) a util.Date (tipo de dato de variable)
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
                UtilidadVentana.mostrarAlertaSinConfirmacion(
                    "Confirmación de modificación",
                    "Las fechas de las sesiones de tutoría académica se modificaron correctamente",
                    Alert.AlertType.INFORMATION);
                UtilidadVentana.cerrarVentana(evento);
            } catch(SQLException ex) {
                UtilidadVentana.mensajePerdidaDeConexion();
            }
            UtilidadVentana.cerrarVentana(evento);
        }
    }

    @FXML
    private void clicCancelar(ActionEvent evento) {
        UtilidadVentana.cerrarVentana(evento);
    }
}
