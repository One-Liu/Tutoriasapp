package uv.fei.tutorias.main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import uv.fei.tutorias.bussinesslogic.PeriodoEscolarDAO;
import uv.fei.tutorias.domain.PeriodoEscolar;

public class SeleccionDePeriodoEscolarControlador implements Initializable {
    @FXML
    private ComboBox<PeriodoEscolar> cbPeriodosEscolares;
    
    private ObservableList<PeriodoEscolar> periodosEscolares = FXCollections.observableArrayList();
    
    public void cargarDatos() throws SQLException {
        PeriodoEscolarDAO periodoEscolarDAO = new PeriodoEscolarDAO();
        this.periodosEscolares.addAll(periodoEscolarDAO.obtenerPeriodosEscolares());
    }
    
    public void cargarCamposGUI() {
        if(this.periodosEscolares.isEmpty()) {
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                "Periodos escolares",
                "No hay periodos escolares registrados",
                Alert.AlertType.ERROR);
            UtilidadVentana.cerrarVentana(new ActionEvent());

        } else {
            this.cbPeriodosEscolares.setItems(periodosEscolares);
            this.cbPeriodosEscolares.getSelectionModel().selectFirst();
            this.cbPeriodosEscolares.setConverter(new StringConverter<PeriodoEscolar>() {
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
        PeriodoEscolar periodoEscolarSeleccionado = this.cbPeriodosEscolares.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUIModificacionDeFechasDeSesionDeTutoria.fxml"));
            Parent raiz = cargadorFXML.load();
            ModificacionDeFechasDeSesionDeTutoriaControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.setPeriodoEscolar(periodoEscolarSeleccionado);
            controladorGUI.cargarDatos();
            controladorGUI.cargarCamposGUI();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Modificación de fechas de sesión de tutoría");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException excepcionIO) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        } catch(SQLException excepcionSQL) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }

}
