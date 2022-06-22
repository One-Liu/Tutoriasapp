package uv.fei.tutorias.main;

import uv.fei.tutorias.utilidades.UtilidadVentana;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import uv.fei.tutorias.bussinesslogic.PeriodoEscolarDAO;
import uv.fei.tutorias.bussinesslogic.SesionDeTutoriaAcademicaDAO;
import uv.fei.tutorias.domain.PeriodoEscolar;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

import java.sql.SQLException;
import java.util.Date;

public class RegistrarFechasDeSesionDeTutoriaControlador {
    @FXML private DatePicker dpInicioPeriodoEscolar;
    @FXML private DatePicker dpFinPeriodoEscolar;
    @FXML private DatePicker dpPrimeraFecha;
    @FXML private DatePicker dpSegundaFecha;
    @FXML private DatePicker dpTerceraFecha;

    public void clicCancelar(ActionEvent actionEvent) {
        UtilidadVentana.cerrarVentana(actionEvent);
    }

    public void clicGuardar(ActionEvent actionEvent) {
        int idPeriodoEscolar = -1;
        if (sesionesDeTutoriasOrdenadas() && sesionesDeTutoriaVanDentroDePeriodoEscolar() && !camposVacios()){
            try {
                idPeriodoEscolar = guardarPeriodoEscolar();
            } catch (SQLException e) {
                UtilidadVentana.mensajePerdidaDeConexion();
            }
            try {
                if (idPeriodoEscolar != -1){
                    guardarFechasDeSeionDeTutoria(idPeriodoEscolar);
                }
            } catch (SQLException e) {
                UtilidadVentana.mensajePerdidaDeConexion();
            }
        }else {
            UtilidadVentana.mostrarAlertaSinConfirmacion("Llene la fechas correctamente","Asegurese de las fechas de las sesiones correspondan al periodo escolar", Alert.AlertType.WARNING);
        }
        limpiarVentana();

    }
    public void guardarFechasDeSeionDeTutoria(int idPeriodoEscolar) throws SQLException {
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica();
        SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDAO = new SesionDeTutoriaAcademicaDAO();

        Date primeraFecha = (Date) java.sql.Date.valueOf(dpPrimeraFecha.getValue());
        Date segundaFecha = (Date) java.sql.Date.valueOf(dpSegundaFecha.getValue());
        Date terceraFecha = (Date) java.sql.Date.valueOf(dpTerceraFecha.getValue());

        sesionDeTutoriaAcademica.setIdPeriodoEscolar(idPeriodoEscolar);

        sesionDeTutoriaAcademica.setFecha(primeraFecha);
        sesionDeTutoriaAcademicaDAO.agregarSesionDeTutoriaAcademica(sesionDeTutoriaAcademica);
        sesionDeTutoriaAcademica.setFecha(segundaFecha);
        sesionDeTutoriaAcademicaDAO.agregarSesionDeTutoriaAcademica(sesionDeTutoriaAcademica);
        sesionDeTutoriaAcademica.setFecha(terceraFecha);
        sesionDeTutoriaAcademicaDAO.agregarSesionDeTutoriaAcademica(sesionDeTutoriaAcademica);



    }

    public int guardarPeriodoEscolar() throws SQLException {
        int idPeriodoEscolar;
        PeriodoEscolar periodoEscolar = new PeriodoEscolar();
        PeriodoEscolarDAO periodoEscolarDAO = new PeriodoEscolarDAO();

        Date inicioPeriodoEscolar = (Date) java.sql.Date.valueOf(dpInicioPeriodoEscolar.getValue());
        Date finPeriodoEscolar = (Date) java.sql.Date.valueOf(dpFinPeriodoEscolar.getValue());

        periodoEscolar.setFechaInicio(inicioPeriodoEscolar);
        periodoEscolar.setFechaTermino(finPeriodoEscolar);

        idPeriodoEscolar = periodoEscolarDAO.agregarPeriodoEscolar(periodoEscolar);

        return idPeriodoEscolar;


    }

    public void clicRegistrarFechaDeCierreEntregaDelReporte(ActionEvent actionEvent) {
    }

    public boolean sesionesDeTutoriaVanDentroDePeriodoEscolar() {
        return dpInicioPeriodoEscolar.getValue().isBefore(dpPrimeraFecha.getValue())
                && dpFinPeriodoEscolar.getValue().isAfter(dpPrimeraFecha.getValue())
                && dpInicioPeriodoEscolar.getValue().isBefore(dpSegundaFecha.getValue())
                && dpFinPeriodoEscolar.getValue().isAfter(dpSegundaFecha.getValue())
                && dpInicioPeriodoEscolar.getValue().isBefore(dpTerceraFecha.getValue())
                && dpFinPeriodoEscolar.getValue().isAfter(dpTerceraFecha.getValue());
    }
    public boolean sesionesDeTutoriasOrdenadas(){
        return dpPrimeraFecha.getValue().isBefore(dpSegundaFecha.getValue())
                && dpSegundaFecha.getValue().isBefore(dpTerceraFecha.getValue());
    }

    public boolean camposVacios(){
        boolean bandera = true;
        if (dpTerceraFecha.getValue() == null){
            UtilidadVentana.mostrarAlertaSinConfirmacion("Error","El campo de la tercera sesion de tutoria esta vacio", Alert.AlertType.ERROR);
        }else if (dpSegundaFecha.getValue() == null){
            UtilidadVentana.mostrarAlertaSinConfirmacion("Error","El campo de la segunda sesion de tutoria esta vacio", Alert.AlertType.ERROR);
        }else if (dpPrimeraFecha.getValue() == null){
            UtilidadVentana.mostrarAlertaSinConfirmacion("Error","El campo de la primera sesion de tutoria esta vacio", Alert.AlertType.ERROR);
        }else if (dpInicioPeriodoEscolar.getValue() == null){
            UtilidadVentana.mostrarAlertaSinConfirmacion("Error","El campo de inicio de periodo escolar esta vacio", Alert.AlertType.ERROR);
        }else if (dpFinPeriodoEscolar.getValue() == null){
            UtilidadVentana.mostrarAlertaSinConfirmacion("Error","El campo de fin de periodo escolar esta vacio", Alert.AlertType.ERROR);
        } else {
            bandera = false;
        }
        return bandera;
    }
    public void limpiarVentana(){
        dpInicioPeriodoEscolar.setValue(null);
        dpFinPeriodoEscolar.setValue(null);
        dpPrimeraFecha.setValue(null);
        dpSegundaFecha.setValue(null);
        dpTerceraFecha.setValue(null);
    }

}
