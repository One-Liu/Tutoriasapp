package uv.fei.tutorias.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lombok.Setter;
import uv.fei.tutorias.bussinesslogic.SesionDeTutoriaAcademicaDAO;
import uv.fei.tutorias.domain.PeriodoEscolar;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SeleccionDeSesionDeTutoria_LlenarReporteDeTutoriaControlador implements Initializable {

    @FXML
    private ComboBox<SesionDeTutoriaAcademica> cbFechasDeSesionDeTutoria;

    private ObservableList<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademica = FXCollections.observableArrayList();

    public void cargarDatos() throws SQLException {
        SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDAO = new SesionDeTutoriaAcademicaDAO();
        this.sesionesDeTutoriaAcademica.addAll(sesionDeTutoriaAcademicaDAO.obtenerSesionesDeTutoriaAcademicaSinOcurrir());
    }

    public void cargarCamposGUI() {
        if(sesionesDeTutoriaAcademica.isEmpty()) {
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                    "Sesiones de tutoría académica",
                    "No hay sesiones de tutoría académica registradas",
                    Alert.AlertType.ERROR);

        } else {
            this.cbFechasDeSesionDeTutoria.setItems(sesionesDeTutoriaAcademica);
            this.cbFechasDeSesionDeTutoria.getSelectionModel().selectFirst();
            this.cbFechasDeSesionDeTutoria.setConverter(new StringConverter<SesionDeTutoriaAcademica>() {
                @Override
                public String toString(SesionDeTutoriaAcademica sesionDeTutoriaAcademica) {
                    return sesionDeTutoriaAcademica == null ? null : sesionDeTutoriaAcademica.getFechaConFormato();
                }

                @Override
                public SesionDeTutoriaAcademica fromString(String string) {
                    throw new UnsupportedOperationException("Operación no soportada");
                }
            });
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void clicCancelar(ActionEvent evento) {
        UtilidadVentana.cerrarVentana(evento);
    }

    @FXML
    private void clicSeleccionar(ActionEvent evento) {
        SesionDeTutoriaAcademica sesionDeTutoriaAcademicaSeleccionada = this.cbFechasDeSesionDeTutoria.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUILlenarReporteDeTutoria.fxml"));
            Parent raiz = cargadorFXML.load();
            LlenarReporteDeTutoriaControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.setSesionDeTutoriaAcademica(sesionDeTutoriaAcademicaSeleccionada);
            controladorGUI.cargarDatos();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Registro de horario de sesión de tutoría");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
            UtilidadVentana.cerrarVentana(evento);
        } catch(IOException ioException) {
            ioException.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
