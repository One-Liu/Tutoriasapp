package uv.fei.tutorias.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.util.StringConverter;
import uv.fei.tutorias.bussinesslogic.FechaDeCierreEntregaDeReporteDAO;
import uv.fei.tutorias.bussinesslogic.PeriodoEscolarDAO;
import uv.fei.tutorias.bussinesslogic.SesionDeTutoriaAcademicaDAO;
import uv.fei.tutorias.domain.FechaDeCierreEntregaDeReporte;
import uv.fei.tutorias.domain.PeriodoEscolar;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class RegistrarFechasDeCierreParaLaEntregaDeReporteControlador implements Initializable {
    @FXML ComboBox<PeriodoEscolar> cbPeriodosEscolares;
    @FXML private Label lblPrimeraSesionDeTutoria;
    @FXML private Label lblSegundaSesionDeTutoria;
    @FXML private Label lblTerceraSesionDeTutoria;
    @FXML private DatePicker dpPrimeraFechaCierre;
    @FXML private DatePicker dpSegundaFechaCierre;
    @FXML private DatePicker dpTerceraFechaCierre;
    private ObservableList<PeriodoEscolar> periodosEscolares = FXCollections.observableArrayList();
    private List<SesionDeTutoriaAcademica> sesionesDeTutoriasAcademicas;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dpPrimeraFechaCierre.setDisable(true);
        dpSegundaFechaCierre.setDisable(true);
        dpTerceraFechaCierre.setDisable(true);
        lblPrimeraSesionDeTutoria.setText("Elija un periodo escolar");
        lblSegundaSesionDeTutoria.setText("Elija un periodo escolar");
        lblTerceraSesionDeTutoria.setText("Elija un periodo escolar");
        cargarDatos();
        cargarCamposGUI();


    }

    public void clicGuardar(ActionEvent actionEvent) {
        if (!camposVacios() && fechasDeCierreCorrectas()){
            try {
                agregarFechaDeCierreEntregaDeReporte();
            } catch (SQLException e) {
                UtilidadVentana.mensajePerdidaDeConexion();
            }
            UtilidadVentana.mostrarAlertaConfirmacion("Todo correcto","Datos registrado en la db", Alert.AlertType.CONFIRMATION);
            UtilidadVentana.cerrarVentana(actionEvent);
        }else {
            UtilidadVentana.mostrarAlertaSinConfirmacion("Verifique fechas","No debe de haber campos vacios y las fechas de entrega son despues de la sesion", Alert.AlertType.WARNING);
        }
    }
    public void agregarFechaDeCierreEntregaDeReporte() throws SQLException {
        Date primeraFechaDeCierre = (Date) java.sql.Date.valueOf(dpPrimeraFechaCierre.getValue());
        Date segundaFechaDeCierre = (Date) java.sql.Date.valueOf(dpSegundaFechaCierre.getValue());
        Date terceraFechaDeCierre = (Date) java.sql.Date.valueOf(dpTerceraFechaCierre.getValue());
        int id1;
        int id2;
        int id3;
        FechaDeCierreEntregaDeReporte fechaDeCierreEntregaDeReporte = new FechaDeCierreEntregaDeReporte();
        FechaDeCierreEntregaDeReporteDAO fechaDeCierreEntregaDeReporteDAO = new FechaDeCierreEntregaDeReporteDAO();
        SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDAO = new SesionDeTutoriaAcademicaDAO();
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica();



        fechaDeCierreEntregaDeReporte.setFecha(primeraFechaDeCierre);
        id1 = fechaDeCierreEntregaDeReporteDAO.agregarFechaDeCierreEntregaDeReporte(fechaDeCierreEntregaDeReporte);
        fechaDeCierreEntregaDeReporte.setFecha(segundaFechaDeCierre);
        id2 = fechaDeCierreEntregaDeReporteDAO.agregarFechaDeCierreEntregaDeReporte(fechaDeCierreEntregaDeReporte);
        fechaDeCierreEntregaDeReporte.setFecha(terceraFechaDeCierre);
        id3 = fechaDeCierreEntregaDeReporteDAO.agregarFechaDeCierreEntregaDeReporte(fechaDeCierreEntregaDeReporte);


        sesionDeTutoriaAcademica.setIdFechaDeCierreEntregaDeReporte(id1);
        sesionDeTutoriaAcademica.setId(sesionesDeTutoriasAcademicas.get(0).getId());
        sesionDeTutoriaAcademicaDAO.modificarFechaDeCierreDeEntregaDeReporte(sesionDeTutoriaAcademica);

        sesionDeTutoriaAcademica.setIdFechaDeCierreEntregaDeReporte(id2);
        sesionDeTutoriaAcademica.setId(sesionesDeTutoriasAcademicas.get(1).getId());
        sesionDeTutoriaAcademicaDAO.modificarFechaDeCierreDeEntregaDeReporte(sesionDeTutoriaAcademica);

        sesionDeTutoriaAcademica.setIdFechaDeCierreEntregaDeReporte(id3);
        sesionDeTutoriaAcademica.setId(sesionesDeTutoriasAcademicas.get(2).getId());
        sesionDeTutoriaAcademicaDAO.modificarFechaDeCierreDeEntregaDeReporte(sesionDeTutoriaAcademica);







    }

    public void clicCancelar(ActionEvent actionEvent) {
        UtilidadVentana.cerrarVentana(actionEvent);
    }

    public void clicSeleccionPeriodoEscolar(ActionEvent actionEvent) {
        dpPrimeraFechaCierre.setDisable(false);
        dpSegundaFechaCierre.setDisable(false);
        dpTerceraFechaCierre.setDisable(false);
        PeriodoEscolar periodoEscolar =  cbPeriodosEscolares.getSelectionModel().getSelectedItem();
        try {
             llenarlabelsConElPeriodoEscolar(periodoEscolar);
        } catch (SQLException e) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }

    private void llenarlabelsConElPeriodoEscolar(PeriodoEscolar periodoEscolar) throws SQLException {
        SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDAO = new SesionDeTutoriaAcademicaDAO();
        sesionesDeTutoriasAcademicas = sesionDeTutoriaAcademicaDAO.obtenerSesionDeTutoriaAcademicaPorPeriodoEscolar(periodoEscolar.getId());

        lblPrimeraSesionDeTutoria.setText(String.valueOf(sesionesDeTutoriasAcademicas.get(0).getFecha()));
        lblSegundaSesionDeTutoria.setText(String.valueOf(sesionesDeTutoriasAcademicas.get(1).getFecha()));
        lblTerceraSesionDeTutoria.setText(String.valueOf(sesionesDeTutoriasAcademicas.get(2).getFecha()));
    }

    private void cargarDatos() {
        PeriodoEscolarDAO periodoEscolarDAO = new PeriodoEscolarDAO();
        try {
            this.periodosEscolares.addAll(periodoEscolarDAO.obtenerPeriodosEscolares());
        } catch(SQLException ex) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }

    private void cargarCamposGUI() {
        cargarCamposGui(this.cbPeriodosEscolares, periodosEscolares);
    }

    static void cargarCamposGui(ComboBox<PeriodoEscolar> cbPeriodosEscolares, ObservableList<PeriodoEscolar> periodosEscolares) {
        cbPeriodosEscolares.setItems(periodosEscolares);
        cbPeriodosEscolares.getSelectionModel().selectFirst();
        cbPeriodosEscolares.setConverter(new StringConverter<PeriodoEscolar>() {
            @Override
            public String toString(PeriodoEscolar periodoEscolar) {
                return periodoEscolar == null ? null : periodoEscolar.getFechas();
            }

            @Override
            public PeriodoEscolar fromString(String string) {
                throw new UnsupportedOperationException("Método no soportado");
            }
        });
    }
    private boolean fechasDeCierreCorrectas(){
        return dpPrimeraFechaCierre.getValue().isAfter(LocalDate.parse(lblPrimeraSesionDeTutoria.getText()))
                && dpPrimeraFechaCierre.getValue().isBefore(LocalDate.parse(lblSegundaSesionDeTutoria.getText()))
                && dpSegundaFechaCierre.getValue().isAfter(LocalDate.parse(lblSegundaSesionDeTutoria.getText()))
                && dpSegundaFechaCierre.getValue().isBefore(LocalDate.parse(lblTerceraSesionDeTutoria.getText()))
                && dpTerceraFechaCierre.getValue().isAfter(LocalDate.parse(lblTerceraSesionDeTutoria.getText()));
    }
    private boolean camposVacios(){
        boolean bandera = true;
        if (dpPrimeraFechaCierre.getValue() == null){
            UtilidadVentana.mostrarAlertaSinConfirmacion("Error","El campo de la tercera sesion de tutoria esta vacio", Alert.AlertType.ERROR);
        }else if (dpSegundaFechaCierre.getValue() == null){
            UtilidadVentana.mostrarAlertaSinConfirmacion("Error","El campo de la segunda sesion de tutoria esta vacio", Alert.AlertType.ERROR);
        }else if (dpTerceraFechaCierre.getValue() == null){
            UtilidadVentana.mostrarAlertaSinConfirmacion("Error","El campo de la primera sesion de tutoria esta vacio", Alert.AlertType.ERROR);
        } else {
            bandera = false;
        }
        return bandera;

    }
}
